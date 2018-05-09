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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;

/**
 * Card service implementation for sending APDUs to a terminal using the
 * JSR 268 (<code>javax.smartcardio</code>) classes available in Java
 * SDK 6.0 and higher.
 *
 * @author Martijn Oostdijk (martijno@cs.ru.nl)
 *
 * @version $Revision$
 */
public class TerminalCardService extends CardService {

  private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");

  private CardTerminal terminal;
  private Card card;
  private CardChannel channel;
  private long lastActiveTime;
  private int apduCount;

  /**
   * Constructs a new card service.
   *
   * @param terminal the card terminal to connect to
   */
  public TerminalCardService(CardTerminal terminal) {
    super();
    this.terminal = terminal;
    lastActiveTime = System.currentTimeMillis();
    apduCount = 0;
  }

  /**
   * Opens a session with the card.
   */
  @Override
  public void open() throws CardServiceException {
    if (isOpen()) {
      return;
    }
    try {
      try {
        /* Prefer T=1. */
        card = terminal.connect("T=1");
      } catch (CardException ce) {
        LOGGER.log(Level.WARNING, "Failed to connect with T=1", ce);

        /* If that fails, connect with any protocol available (probably T=0). */
        card = terminal.connect("*");
      }
      channel = card.getBasicChannel();
      if (channel == null) {
        throw new CardServiceException("channel == null");
      }
      state = SESSION_STARTED_STATE;
    } catch (CardException ce) {
      throw new CardServiceException("Exception opening connection to terminal", ce);
    }
  }

  /**
   * Whether there is an open session with the card.
   */
  @Override
  public boolean isOpen() {
    return (state != SESSION_STOPPED_STATE);
  }

  /**
   * Sends an APDU to the card.
   *
   * @param ourCommandAPDU the command apdu to send
   * 
   * @return the response from the card, including the status word
   * 
   * @throws CardServiceException - if the card operation failed
   */
  @Override
  public ResponseAPDU transmit(CommandAPDU ourCommandAPDU) throws CardServiceException {
    try {
      if (channel == null) {
        throw new CardServiceException("channel == null");
      }
      javax.smartcardio.CommandAPDU command = new javax.smartcardio.CommandAPDU(ourCommandAPDU.getBytes());
      javax.smartcardio.ResponseAPDU response = channel.transmit(command);
      ResponseAPDU ourResponseAPDU = new ResponseAPDU(response.getBytes());
      notifyExchangedAPDU(new APDUEvent(this, "RAW", ++apduCount, ourCommandAPDU, ourResponseAPDU));
      lastActiveTime = System.currentTimeMillis();
      return ourResponseAPDU;
    } catch (CardException ce) {
      throw new CardServiceException("Exception during transmit", ce);
    }
  }

  @Override
  public byte[] getATR() {
    javax.smartcardio.ATR atr = channel.getCard().getATR();
    return atr.getBytes();
  }

  @Override
  public boolean isExtendedAPDULengthSupported() {
    //		javax.smartcardio.ATR atr = channel.getCard().getATR();
    //		byte[] historicalBytes = atr.getHistoricalBytes();
    return true; // FIXME: check ATR to see if really true
  }

  /**
   * Sends a control command to the terminal
   *
   * @param controlCode the control code to send
   * @param command the command data for the terminal
   * 
   * @return response from the terminal/card
   * 
   * @throws CardServiceException - if the card operation failed
   */
  public byte[] transmitControlCommand(int controlCode, byte[] command) throws CardServiceException {
    try {
      return card.transmitControlCommand(controlCode, command);
    } catch (CardException ce) {
      throw new CardServiceException("Exception during transmit", ce);
    }
  }

  /**
   * Closes the session with the card.
   */
  @Override
  public void close() {
    try {
      if (card != null && terminal.isCardPresent()) {
        /*
         * WARNING: Woj: the meaning of the reset flag is actually
         * reversed w.r.t. to the official documentation, false means
         * that the card is going to be reset, true means do not reset
         * This is a bug in the smartcardio implementation from SUN
         * Moreover, Linux PCSC implementation goes numb if you try to
         * disconnect a card that is not there anymore.
         */
        card.disconnect(false);
      }
      state = SESSION_STOPPED_STATE;
    } catch (Exception e) {
      /* Disconnect failed? Fine... */
      LOGGER.log(Level.FINE, "Exception closing service", e);
    }
  }

  /**
   * The terminal used by this service.
   *
   * @return a terminal
   */
  public CardTerminal getTerminal() {
    return terminal;
  }

  /* package visible */ long getLastActiveTime() {
    return lastActiveTime;
  }

  /**
   * Produces a textual representation of this service.
   *
   * @return a textual representation of this service
   */
  @Override
  public String toString() {
    return "TerminalCardService [" + terminal.getName() + "]";
  }
  
  /**
   * Determines whether an exception indicates a tag is lost event.
   *
   * @param e an exception
   *
   * @return whether the exception indicates a tag is lost event
   */
  public boolean isConnectionLost(Exception e) {
    if (e == null) {
      return false;
    }

    String message = e.getMessage();
    if (message == null) {
      message = "";
    }

    /*
     * Check whether exception is likely caused by a
     * sun.security.smartcardio.PCSCException
     * indicating card has been removed.
     */
    Throwable cause = null;
    Throwable rootCause = e;

    while(null != (cause = rootCause.getCause())  && (rootCause != cause) ) {
      rootCause = cause;
    }

    String rootMessage = rootCause.getMessage();
    if (rootMessage == null) {
      rootMessage = "";
    }
    if ("SCARD_W_REMOVED_CARD".equals(rootMessage)) {
      return true;
    }

    if (rootMessage != null && rootMessage.contains("SCARD_E_NOT_TRANSACTED")) {
      return true;
    }

    return false;
  }
}
