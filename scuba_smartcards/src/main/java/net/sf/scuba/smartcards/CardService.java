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
 * Copyright (C) 2009 - 2023  The SCUBA team.
 *
 * $Id$
 */

package net.sf.scuba.smartcards;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default abstract service.
 * Provides a factory method for creating card services.
 * Provides some functionality for observing apdu events.
 *
 * @author Cees-Bart Breunesse (ceesb@cs.ru.nl)
 * @author Martijn Oostdijk (martijno@cs.ru.nl)
 * @author Pim Vullers (pim@cs.ru.nl)
 *
 * @version $Revision$
 */
public abstract class CardService {

  protected static final int SESSION_STOPPED_STATE = 0;
  protected static final int SESSION_STARTED_STATE = 1;

  private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");

  private static final Map<String, String> objectToServiceMap;
  static {
    objectToServiceMap = new HashMap<String, String>();
    objectToServiceMap.put("javax.smartcardio.CardTerminal", "net.sf.scuba.smartcards.TerminalCardService");
    objectToServiceMap.put("sun.security.smartcardio.TerminalImpl", "net.sf.scuba.smartcards.TerminalCardService");
    objectToServiceMap.put("android.nfc.tech.IsoDep", "net.sf.scuba.smartcards.IsoDepCardService");
  }

  /** The apduListeners. */
  private Collection<APDUListener> apduListeners;

  protected int state;

  /**
   * Creates a new service.
   */
  public CardService() {
    this.apduListeners = new HashSet<APDUListener>();
    this.state = SESSION_STOPPED_STATE;
  }

  /**
   * Creates a card service.
   *
   * @param object some platform object responsible for transporting the APDU
   *
   * @return a card service
   */
  public static CardService getInstance(Object object) {
    if (object == null) {
      throw new IllegalArgumentException();
    }
    Class<?> objectClass = object.getClass();
    String objectClassName = objectClass.getCanonicalName();
    for (Entry<String, String> entry: objectToServiceMap.entrySet()) {
      String targetObjectClassName = entry.getKey();
      try {
        Class<?> targetObjectClass = Class.forName(targetObjectClassName);
        String serviceClassName = entry.getValue();
        if (targetObjectClass.isInstance(object)) {
          try {
            Class<?> cardServiceClass = Class.forName(serviceClassName);
            return (CardService)cardServiceClass.getConstructor(targetObjectClass).newInstance(object);
          } catch (Exception e) {
            throw new IllegalArgumentException(e);
          }
        }
      } catch (ClassNotFoundException cnfe) {
        LOGGER.log(Level.FINEST, "Could not find class, trying next one", cnfe);
        continue;
      }
    }
    throw new IllegalArgumentException("Could not find a CardService for object of class \"" + objectClassName + "\"");
  }

  /**
   * Adds a listener.
   *
   * @param l the listener to add
   */
  public void addAPDUListener(APDUListener l) {
    if (apduListeners != null && l != null) {
      apduListeners.add(l);
    }
  }

  /**
   * Removes a listener.
   * If the specified listener is not present, this method has no effect.
   *
   * @param l the listener to remove
   */
  public void removeAPDUListener(APDUListener l) {
    if (apduListeners != null) {
      apduListeners.remove(l);
    }
  }

  /**
   * Returns the collection of APDU listeners.
   * The result is an unmodifiable copy of the actual listeners at
   * the moment of calling.
   *
   * @return the APDU listeners
   */
  public Collection<APDUListener> getAPDUListeners() {
    return Collections.unmodifiableCollection(apduListeners);
  }

  /**
   * Notifies listeners about APDU event.
   *
   * @param event the APDU event
   */
  protected void notifyExchangedAPDU(APDUEvent event) {
    if (apduListeners == null || apduListeners.isEmpty()) {
      return;
    }

    for (APDUListener listener: apduListeners) {
      listener.exchangedAPDU(event);
    }
  }

  /**
   * Opens a session with the card. Selects a reader. Connects to the card.
   * Notifies any interested apduListeners.
   *
   * @throws CardServiceException on error
   */
  public abstract void open() throws CardServiceException;

  /**
   * Whether there is a session started with the card.
   *
   * @return a boolean indicating whether sessions has started
   */
  public abstract boolean isOpen();

  /**
   * Sends an APDU to the card. Notifies any interested apduListeners.
   *
   * This method does not throw a CardServiceException if the ResponseAPDU
   * is status word indicating error.
   *
   * @param commandAPDU the Command APDU to send
   *
   * @return the Response APDU from the card, including the status word
   *
   * @throws CardServiceException if the card operation failed
   */
  public abstract ResponseAPDU transmit(CommandAPDU commandAPDU) throws CardServiceException;

  /**
   * Gets the answer to reset.
   *
   * @return the answer to reset
   *
   * @throws CardServiceException on error
   */
  public abstract byte[] getATR() throws CardServiceException;

  /**
   * Returns a boolean indicating whether extended length APDUs are supported.
   *
   * @return a boolean indicating whether extended length APDUs are supported
   */
  public boolean isExtendedAPDULengthSupported() {
    return false;
  }

  /**
   * Closes the session with the card. Disconnects from the card and reader.
   * Notifies any interested apduListeners.
   */
  public abstract void close();

  /**
   * Determines whether an exception indicates a tag is lost event.
   *
   * @param e an exception
   *
   * @return whether the exception indicates a tag is lost event
   */
  public abstract boolean isConnectionLost(Exception e);
}
