/*
 * This file is part of the SCUBA smart card framework.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * Copyright (C) 2009 - 2018  The SCUBA team.
 *
 * $Id$
 */

package net.sf.scuba.smartcards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;

/**
 * Manages all card terminals.
 *
 * This is the source of card insertion and removal events. Ideally this should
 * be the only place where low level CardService instances (such as {@link
 * net.sf.scuba.smartcards.TerminalCardService TerminalCardService})
 * are created.
 *
 * @author Martijn Oostdijk (martijn.oostdijk@gmail.com)
 * @author Wojciech Mostowski (woj@cs.ru.nl)
 * @author Pim Vullers (pim@cs.ru.nl)
 *
 * @version $Revision$
 */
public class CardManager {

  private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");

  private static final CardManager INSTANCE = new CardManager();
  private static final int FACTORY_POLL_INTERVAL = 950;
  private static final int TERMINAL_POLL_INTERVAL = 450;

  private static final Comparator<TerminalFactory> FACTORY_COMPARATOR = new Comparator<TerminalFactory>() {
    public int compare(TerminalFactory o1, TerminalFactory o2) {
      return o1.getType().compareToIgnoreCase(o2.getType());
    }
  };

  private static final Comparator<CardTerminal> TERMINAL_COMPARATOR = new Comparator<CardTerminal>() {
    public int compare(CardTerminal o1, CardTerminal o2) {
      return o1.getName().compareToIgnoreCase(o2.getName());
    }
  };

  private Map<TerminalFactory, FactoryPoller> factories;
  private Map<CardTerminal, TerminalPoller> terminals;
  private Collection<TerminalFactoryListener> terminalFactoryListeners;
  private Collection<CardTerminalListener> cardTerminalListeners;
  private Collection<APDUListener> apduListeners;

  private Lock terminalFactoryListenersLock;
  private Condition notWaitingForFirstTerminalFactoryListenerCondition;

  private Lock cardTerminalListenersLock;
  private Condition notWaitingForFirstCardTerminalListenerCondition;

  private CardManager() {
    try {
      terminalFactoryListeners = new HashSet<TerminalFactoryListener>();
      cardTerminalListeners = new HashSet<CardTerminalListener>();
      apduListeners = new HashSet<APDUListener>();
      terminalFactoryListenersLock = new ReentrantLock(true);
      notWaitingForFirstTerminalFactoryListenerCondition = terminalFactoryListenersLock.newCondition();
      cardTerminalListenersLock = new ReentrantLock(true);
      notWaitingForFirstCardTerminalListenerCondition = cardTerminalListenersLock.newCondition();

      factories = new HashMap<TerminalFactory, FactoryPoller>();
      terminals = new HashMap<CardTerminal, TerminalPoller>();

      addFactories();
    } catch (Exception ex) {
      LOGGER.log(Level.WARNING, "Exception while adding factories and terminals", ex);
    }
  }

  /**
   * Starts polling all registered factories and terminals (if not already doing so).
   */
  public void startPolling() {
    for (TerminalFactory factory: getFactories()) {
      startPolling(factory);
    }
    for (CardTerminal terminal: getTerminals()) {
      startPolling(terminal);
    }
  }

  /**
   * Starts polling <code>factory</code> (if not already doing so).
   *
   * @param factory a terminal factory
   */
  public void startPolling(TerminalFactory factory) {
    FactoryPoller poller = factories.get(factory);
    if (poller == null) {
      poller = new FactoryPoller(factory, this);
    }
    try {
      poller.startPolling();
    } catch (InterruptedException ie) {
      LOGGER.log(Level.FINEST, "Interrupted while starting polling", ie);
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Starts polling <code>terminal</code> (if not already doing so).
   *
   * @param terminal a card terminal
   */
  public void startPolling(CardTerminal terminal) {
    TerminalPoller poller = terminals.get(terminal);
    if (poller == null) {
      poller = new TerminalPoller(terminal, this);
    }

    try {
      poller.startPolling();
    } catch (InterruptedException ie) {
      LOGGER.log(Level.FINEST, "Interrupted while starting polling", ie);
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Stops polling all registered factories and terminals
   */
  public void stopPolling() {
    for (TerminalFactory factory: getFactories()) {
      stopPolling(factory);
    }

    for (CardTerminal terminal: getTerminals()) {
      stopPolling(terminal);
    }
  }

  /**
   * Stops polling <code>factory</code>.
   *
   * @param factory a terminal factory
   */
  public void stopPolling(TerminalFactory factory) {
    FactoryPoller poller = factories.get(factory);
    if (poller == null) {
      return;
    }

    try {
      poller.stopPolling();
    } catch (InterruptedException ie) {
      LOGGER.log(Level.FINEST, "Interrupted while starting polling", ie);
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Stops polling <code>terminal</code>.
   *
   * @param terminal a card terminal
   */
  public void stopPolling(CardTerminal terminal) {
    TerminalPoller poller = terminals.get(terminal);
    if (poller == null) {
      return;
    }

    try {
      poller.stopPolling();
    } catch (InterruptedException ie) {
      LOGGER.log(Level.FINEST, "Interrupted while starting polling", ie);
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Whether we are polling <code>factory</code>.
   *
   * @param factory a terminal factory
   *
   * @return a boolean
   */
  public boolean isPolling(TerminalFactory factory) {
    FactoryPoller poller = factories.get(factory);
    if (poller == null) {
      return false;
    }

    return poller.isPolling();
  }

  /**
   * Whether we are polling <code>terminal</code>.
   *
   * @param terminal a card terminal
   *
   * @return a boolean
   */
  public boolean isPolling(CardTerminal terminal) {
    TerminalPoller poller = terminals.get(terminal);
    if (poller == null) {
      return false;
    }

    return poller.isPolling();
  }

  /**
   * Gets the service associated with <code>terminal</code> (or <code>null</code> if
   * we are not polling <code>terminal</code>).
   *
   * @param terminal a card terminal
   *
   * @return a card service or <code>null</code>
   */
  public CardService getService(CardTerminal terminal) {
    TerminalPoller poller = terminals.get(terminal);
    if (poller == null) {
      return null;
    }

    return poller.getService();
  }
  /**
   * Whether the card manager is running.
   *
   * @return a boolean indicating whether the card manager is running.
   */
  public boolean isPolling() {
    boolean isPolling = false;

    for (TerminalFactory factory: factories.keySet()) {
      isPolling ^= isPolling(factory);
    }

    for (CardTerminal terminal: terminals.keySet()) {
      isPolling ^= isPolling(terminal);
    }

    return isPolling;
  }

  private void addFactories() {
    addFactory(TerminalFactory.getDefault(), true);
  }

  /**
   * Adds a factory.
   *
   * @param factory the factory to add
   * @param isPolling whether we should immediately start polling
   */
  public void addFactory(TerminalFactory factory, boolean isPolling) {
    FactoryPoller poller = factories.get(factory);
    if (poller == null) {
      poller = new FactoryPoller(factory, this);
      factories.put(factory, poller);
    }
    if (isPolling && !isPolling(factory)) {
      startPolling(factory);
    }
    if (!isPolling && isPolling(factory)) {
      stopPolling(factory);
    }
  }

  /**
   * Adds the terminals produced by the given terminal factory.
   *
   * @param factory the terminal factory
   * @param isPolling should we start polling these terminals initially
   *
   * @return the number of terminals added
   */
  public int addTerminals(TerminalFactory factory, boolean isPolling) {
    try {
      CardTerminals additionalTerminals = factory.terminals();
      if (additionalTerminals == null) {
        return 0;
      }

      List<CardTerminal> additionalTerminalsList = additionalTerminals.list();
      if (additionalTerminalsList == null) {
        return 0;
      }

      List<CardTerminal> terminalsList = new ArrayList<CardTerminal>();
      terminalsList.addAll(additionalTerminalsList);
      for (CardTerminal terminal: terminalsList) {
        addTerminal(terminal, isPolling);
      }

      return additionalTerminalsList.size();
    } catch (CardException ce) {
      LOGGER.log(Level.FINE, "Listing of readers failed. Not adding anything.", ce);
    }

    return 0;
  }

  /**
   * Adds a terminal.
   *
   * @param terminal the card terminal to add
   * @param isPolling whether we should immediately start polling this terminal
   */
  public void addTerminal(CardTerminal terminal, boolean isPolling) {
    TerminalPoller poller = terminals.get(terminal);
    if (poller == null) {
      poller = new TerminalPoller(terminal, this);
      terminals.put(terminal, poller);
    }
    if (isPolling && !isPolling(terminal)) {
      startPolling(terminal);
    }
    if (!isPolling && isPolling(terminal)) {
      stopPolling(terminal);
    }
  }

  /**
   * Remove a terminal.
   *
   * @param terminal the card terminal to remove
   */
  public void removeTerminal(CardTerminal terminal) {
    stopPolling(terminal);
    terminals.remove(terminal);
  }

  /**
   * Adds a listener.
   *
   * @param l the listener to add
   */
  public void addTerminalFactoryListener(TerminalFactoryListener l) {
    terminalFactoryListenersLock.lock();
    try {
      terminalFactoryListeners.add(l);
      notWaitingForFirstTerminalFactoryListenerCondition.signalAll();
    } finally {
      terminalFactoryListenersLock.unlock();
    }
  }

  /**
   * Adds a listener.
   *
   * @param l the listener to add
   */
  public void addCardTerminalListener(CardTerminalListener l) {
    cardTerminalListenersLock.lock();
    try {
      cardTerminalListeners.add(l);
      notWaitingForFirstCardTerminalListenerCondition.signalAll();
    } finally {
      cardTerminalListenersLock.unlock();
    }
  }

  /**
   * Removes a listener.
   *
   * @param l the listener to remove
   */
  public void removeTerminalFactoryListener(TerminalFactoryListener l) {
    terminalFactoryListeners.remove(l);
  }

  /**
   * Removes a listener.
   *
   * @param l the listener to remove
   */
  public void removeCardTerminalListener(CardTerminalListener l) {
    cardTerminalListeners.remove(l);
  }

  /**
   * Adds a listener.
   *
   * @param l the listener to add
   */
  public void addAPDUListener(APDUListener l) {
    apduListeners.add(l);
    for (Map.Entry<CardTerminal, TerminalPoller> entry: terminals.entrySet()) {
      TerminalPoller poller = entry.getValue();
      if (poller == null) {
        continue;
      }
      CardService service = poller.getService();
      if (service == null) {
        continue;
      }
      service.addAPDUListener(l);
    }
  }

  /**
   * Removes a listener.
   *
   * @param l the listener to remove
   */
  public void removeAPDUListener(APDUListener l) {
    apduListeners.remove(l);
    for (Map.Entry<CardTerminal, TerminalPoller> entry: terminals.entrySet()) {
      TerminalPoller poller = entry.getValue();
      if (poller == null) {
        continue;
      }
      CardService service = poller.getService();
      if (service == null) {
        continue;
      }
      service.removeAPDUListener(l);
    }
  }

  private void notifyCardTerminalEvent(final CardTerminalEvent cte) {
    for (final TerminalFactoryListener l: terminalFactoryListeners) {
      (new Thread(new Runnable() {
        public void run() {
          switch (cte.getType()) {
            case CardTerminalEvent.ADDED:
              l.cardTerminalAdded(cte);
              break;
            case CardTerminalEvent.REMOVED:
              l.cardTerminalRemoved(cte);
              break;
            default:
              LOGGER.info("Unknown card event type: " + cte.getType());
              break;
          }
        }
      })).start();
    }
  }

  private void notifyCardEvent(final CardEvent ce) {
    for (final CardTerminalListener l: cardTerminalListeners) {
      (new Thread(new Runnable() {
        public void run() {
          switch (ce.getType()) {
            case CardEvent.INSERTED:
              l.cardInserted(ce);
              break;
            case CardEvent.REMOVED:
              l.cardRemoved(ce);
              break;
            default:
              LOGGER.info("Unknown card event type: " + ce.getType());
              break;
          }
        }
      })).start();
    }
  }

  private boolean hasNoTerminalFactoryListeners() {
    return terminalFactoryListeners.isEmpty();
  }

  private boolean hasNoCardTerminalListeners() {
    return cardTerminalListeners.isEmpty();
  }

  /**
   * Gets a list of factories.
   *
   * @return a list of factories
   */
  public List<TerminalFactory> getFactories() {
    List<TerminalFactory> result = new ArrayList<TerminalFactory>();
    result.addAll(factories.keySet());
    Collections.sort(result, FACTORY_COMPARATOR);
    return result;
  }

  /**
   * Gets a list of terminals.
   *
   * @return a list of terminals
   */
  public List<CardTerminal> getTerminals() {
    List<CardTerminal> result = new ArrayList<CardTerminal>();
    result.addAll(terminals.keySet());
    Collections.sort(result, TERMINAL_COMPARATOR);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("CardManager: [");
    boolean isFirst = true;
    for (Map.Entry<CardTerminal, TerminalPoller> entry: terminals.entrySet()) {
      result.append((isFirst ? "" : ", ") + terminals.get(entry.getKey()));
      if (isFirst) {
        isFirst = false;
      }
    }
    result.append("]");
    return result.toString();
  }

  /**
   * Gets the card manager.
   * By default only PC/SC terminals are added,
   * use {@link #addTerminals(TerminalFactory,boolean)} to add additional terminals.
   *
   * @return the card manager
   */
  public static CardManager getInstance() {
    return INSTANCE;
  }

  private class FactoryPoller implements Runnable {

    private CardManager cm;
    private TerminalFactory factory;
    private boolean isPolling;
    private boolean hasStoppedPolling;
    private Thread myThread;
    private Set<CardTerminal> myTerminals;

    private Lock pollingLock;
    private Condition startedPollingCondition;
    private Condition stoppedPollingCondition;

    public FactoryPoller(TerminalFactory factory, CardManager cm) {
      this.factory = factory;
      this.isPolling = false;
      this.hasStoppedPolling = true;
      this.cm = cm;

      myTerminals = new HashSet<CardTerminal>();
      pollingLock = new ReentrantLock(true);
      stoppedPollingCondition = pollingLock.newCondition();
      startedPollingCondition = pollingLock.newCondition();
    }

    public boolean isPolling() {
      return isPolling;
    }

    public void startPolling() throws InterruptedException {
      pollingLock.lock();
      try {
        if (isPolling) {
          return;
        }

        isPolling = true;
        if (myThread != null && myThread.isAlive()) {
          return;
        }

        myThread = new Thread(this);
        myThread.start();
        while (isPolling && hasStoppedPolling) {
          startedPollingCondition.await();
        }
      } finally {
        pollingLock.unlock();
      }
    }

    public void stopPolling() throws InterruptedException {
      pollingLock.lock();
      try {
        if (!isPolling) {
          return;
        }
        isPolling = false;

        /* Wake up threads waiting for first listener. */
        terminalFactoryListenersLock.lock();
        try {
          notWaitingForFirstTerminalFactoryListenerCondition.signalAll();
        } finally {
          terminalFactoryListenersLock.unlock();
        }

        if (myThread == null || !myThread.isAlive()) {
          return;
        }

        /* Wait until thread has stopped polling. */
        while (!isPolling && !hasStoppedPolling) {
          stoppedPollingCondition.await();
        }
        myThread = null;
      } finally {
        pollingLock.unlock();
      }
    }


    public void run() {
      pollingLock.lock();
      try {
        hasStoppedPolling = false;
        startedPollingCondition.signalAll();
      } finally {
        pollingLock.unlock();
      }
      try {
        while (isPolling) {
          /* If Card Manager has no listeners, we go to sleep. */
          terminalFactoryListenersLock.lock();
          try {
            while (isPolling && cm.hasNoTerminalFactoryListeners()) {
              notWaitingForFirstTerminalFactoryListenerCondition.await();
            }
          } finally {
            terminalFactoryListenersLock.unlock();
          }

          pollingLock.lock();
          try {
            try {
              if (!isPolling) {
                break;
              }

              CardTerminals additionalTerminals = factory.terminals();
              if (additionalTerminals != null) {
                List<CardTerminal> terminalsList = additionalTerminals.list();
                if (terminalsList != null) {
                  Set<CardTerminal> addedTerminals = new HashSet<CardTerminal>(terminalsList);
                  addedTerminals.removeAll(myTerminals);
                  addTerminals(addedTerminals);

                  Set<CardTerminal> removedTerminals = new HashSet<CardTerminal>(myTerminals);
                  removedTerminals.removeAll(terminalsList);
                  removeTerminals(removedTerminals);
                }
              }
            } catch (CardException ce) {
              if (ce.getCause().getMessage().contains("SCARD_E_NO_READERS_AVAILABLE")) {
                removeTerminals(myTerminals);
              } else {
                LOGGER.log(Level.WARNING, "Exception", ce);
              }
            } finally {
              Thread.sleep(FACTORY_POLL_INTERVAL);
            }
          } finally {
            pollingLock.unlock();
          }
        }

        pollingLock.lock();
        try {
          hasStoppedPolling = true;
          stoppedPollingCondition.signalAll();
        } finally {
          pollingLock.unlock();
        }
      } catch (InterruptedException ie) {
        LOGGER.log(Level.FINEST, "Interrupted", ie);
        Thread.currentThread().interrupt();
      }
    }

    /**
     * @param terminals
     */
    private void removeTerminals(Set<CardTerminal> terminals) {
      for (CardTerminal terminal: terminals) {
        removeTerminal(terminal);
        myTerminals.remove(terminal);
        final CardTerminalEvent cte = new CardTerminalEvent(CardTerminalEvent.REMOVED, terminal);
        notifyCardTerminalEvent(cte);
      }
    }

    /**
     * @param terminals
     */
    private void addTerminals(Set<CardTerminal> terminals) {
      for (CardTerminal terminal: terminals) {
        myTerminals.add(terminal);
        addTerminal(terminal, isPolling);
        final CardTerminalEvent cte = new CardTerminalEvent(CardTerminalEvent.ADDED, terminal);
        notifyCardTerminalEvent(cte);
      }
    }

    @Override
    public String toString() {
      return "Poller for " + factory.getType() + (isPolling ? " (polling)" : " (not polling)");
    }
  }

  private class TerminalPoller implements Runnable {

    private CardManager cm;
    private CardTerminal terminal;
    private TerminalCardService service;
    private boolean isPolling;
    private boolean hasStoppedPolling;
    private Thread myThread;

    private Lock pollingLock;
    private Condition startedPollingCondition;
    private Condition stoppedPollingCondition;

    public TerminalPoller(CardTerminal terminal, CardManager cm) {
      this.terminal = terminal;
      this.service = null;
      this.isPolling = false;
      this.hasStoppedPolling = true;
      this.cm = cm;
      this.pollingLock = new ReentrantLock(true);
      stoppedPollingCondition = pollingLock.newCondition();
      startedPollingCondition = pollingLock.newCondition();
    }

    public boolean isPolling() {
      return isPolling;
    }

    public void startPolling() throws InterruptedException {
      pollingLock.lock();
      try {
        if (isPolling) {
          return;
        }

        isPolling = true;

        if (myThread != null && myThread.isAlive()) {
          return;
        }

        myThread = new Thread(this);
        myThread.start();

        while (isPolling && hasStoppedPolling) {
          startedPollingCondition.await();
        }
      } finally {
        pollingLock.unlock();
      }
    }

    public void stopPolling() throws InterruptedException {
      pollingLock.lock();
      try {
        if (!isPolling) {
          return;
        }

        /* Actually stop polling: this will quit the loop in the run method. */
        isPolling = false;

        /* Wake up threads waiting for first listener. */
        cardTerminalListenersLock.lock();
        try {
          notWaitingForFirstCardTerminalListenerCondition.signalAll();
        } finally {
          cardTerminalListenersLock.unlock();
        }

        if (myThread == null || !myThread.isAlive()) {
          return;
        }

        /* Wait until thread has stopped polling. */
        while (!isPolling && !hasStoppedPolling) {
          stoppedPollingCondition.await();
        }
        myThread = null;
      } finally {
        pollingLock.unlock();
      }
    }

    public CardService getService() {
      return service;
    }

    public void run() {
      pollingLock.lock();
      try {
        hasStoppedPolling = false;
        startedPollingCondition.signalAll();
      } finally {
        pollingLock.unlock();
      }

      try {
        while (isPolling) {
          /* If Card Manager has no listeners, we go to sleep. */
          cardTerminalListenersLock.lock();
          try {
            while (isPolling && cm.hasNoCardTerminalListeners()) {
              notWaitingForFirstCardTerminalListenerCondition.await();
            }
          } finally {
            cardTerminalListenersLock.unlock();
          }

          pollingLock.lock();
          try {
            try {
              if (!isPolling) {
                break;
              }

              boolean wasCardPresent = false;
              boolean isCardPresent = false;
              long currentTime = System.currentTimeMillis();
              if (service != null) {
                wasCardPresent = true;
              } else {
                try {
                  if (terminal.isCardPresent()) {
                    service = new TerminalCardService(terminal);
                    for (APDUListener l: cm.apduListeners) {
                      service.addAPDUListener(l);
                    }
                  }
                } catch (Exception e) {
                  LOGGER.log(Level.FINE, "Exception testing card presence", e);
                  if (service != null) {
                    service.close();
                  }
                }
              }

              if (service != null && (currentTime - service.getLastActiveTime() < TERMINAL_POLL_INTERVAL)) {
                isCardPresent = true;
              } else {
                try {
                  isCardPresent = terminal.isCardPresent();
                } catch (Exception e) {
                  LOGGER.log(Level.FINE, "Exception testing card presence", e);
                  isCardPresent = false;
                }
              }

              if (wasCardPresent && !isCardPresent) {
                if (service != null) {
                  final CardEvent ce = new CardEvent(CardEvent.REMOVED, service);
                  notifyCardEvent(ce);
                  service.close();
                }
                service = null;
              } else if (!wasCardPresent && isCardPresent) {
                if (service != null) {
                  final CardEvent ce = new CardEvent(CardEvent.INSERTED, service);
                  notifyCardEvent(ce);
                }
              }

              // // This doesn't seem to work on some variants of Linux + pcsclite. :(
              //		if (isCardPresent) {
              //			terminal.waitForCardAbsent(TERMINAL_POLL_INTERVAL); // or longer..
              //		} else {
              //			terminal.waitForCardPresent(TERMINAL_POLL_INTERVAL); // or longer..
              //		}
              // // ... so we'll just sleep for a while as a courtesy to other threads...
              Thread.sleep(TERMINAL_POLL_INTERVAL);
            } catch (Exception e) {
              /* FIXME: what if reader no longer connected, should we remove it from list? */
              LOGGER.log(Level.WARNING, "Exception", e);
            } finally {
              if (!isPolling && service != null) {
                service.close();
              }
            }
          } finally {
            pollingLock.unlock();
          }
        }

        pollingLock.lock();
        try {
          hasStoppedPolling = true;
          stoppedPollingCondition.signalAll();
        } finally {
          pollingLock.unlock();
        }
      } catch (InterruptedException ie) {
        LOGGER.log(Level.FINEST, "Interrupted", ie);
        Thread.currentThread().interrupt();
      }
    }

    @Override
    public String toString() {
      return "Poller for " + terminal.getName() + (isPolling ? " (polling)" : " (not polling)");
    }
  }
}
