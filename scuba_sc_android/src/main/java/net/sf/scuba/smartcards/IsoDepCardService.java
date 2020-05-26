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
 * Inspired by the work of Max Guenther (max.math.guenther@googlemail.com) for
 * aJMRTD (an Android client for JMRTD, released under the LGPL license).
 *
 * Copyright (C) 2009-2013 The SCUBA team.
 *
 * $Id: $
 */

package net.sf.scuba.smartcards;

import java.io.IOException;

import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;

import android.annotation.TargetApi;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.os.Build;

/**
 * Card service implementation for sending APDUs to a terminal using the
 * Android NFC (<code>android.nfc.tech</code>) classes available in Android
 * SDK 2.3.3 (API 10) and higher.
 *
 * @author Pim Vullers (pim@cs.ru.nl)
 *
 * @version $Revision: 214 $
 */
public class IsoDepCardService extends CardService {

  private IsoDep isoDep;
  private int apduCount;

  /**
   * Constructs a new card service.
   *
   * @param isoDep the card terminal to connect to
   */
  public IsoDepCardService(IsoDep isoDep) {
    this.isoDep = isoDep;
    apduCount = 0;
  }

  /**
   * Opens a session with the card.
   */
  public void open() throws CardServiceException {
    if (isOpen()) {
      return;
    }

    try {
      isoDep.connect();
      if (!isoDep.isConnected()) {
        throw new CardServiceException("Failed to connect");
      }
      state = SESSION_STARTED_STATE;
    } catch (IOException e) {
      throw new CardServiceException("Failed to connect", e);
    }
  }

  /**
   * Whether there is an open session with the card.
   */
  public boolean isOpen() {
    if (isoDep.isConnected()) {
      state = SESSION_STARTED_STATE;
      return true;
    } else {
      state = SESSION_STOPPED_STATE;
      return false;
    }
  }

  /**
   * Sends an APDU to the card.
   *
   * @param ourCommandAPDU the command apdu to send
   * @return the response from the card, including the status word
   * @throws CardServiceException - if the card operation failed
   */
  public ResponseAPDU transmit(CommandAPDU ourCommandAPDU) throws CardServiceException {
    try {
      if (!isoDep.isConnected()) {
        throw new CardServiceException("Not connected");
      }
      byte[] responseBytes = isoDep.transceive(ourCommandAPDU.getBytes());
      if (responseBytes == null || responseBytes.length < 2) {
        throw new CardServiceException("Failed response");
      }
      ResponseAPDU ourResponseAPDU = new ResponseAPDU(responseBytes);
      notifyExchangedAPDU(new APDUEvent(this, "ISODep", ++apduCount, ourCommandAPDU, ourResponseAPDU));
      return ourResponseAPDU;
    } catch (CardServiceException cse) {
      throw cse;
    } catch (Exception e) {
      throw new CardServiceException("Could not tranceive APDU", e);
    }
  }

  public byte[] getATR() {
    if (isoDep == null) {
      return null;
    }

    Tag tag = isoDep.getTag();
    if (tag == null) {
      return null;
    }

    NfcA nfcA = NfcA.get(tag);
    if (nfcA != null) {
      return isoDep.getHistoricalBytes();
    }

    NfcB nfcB = NfcB.get(tag);
    if (nfcB != null) {
      return isoDep.getHiLayerResponse();
    }

    return isoDep.getHistoricalBytes();
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public boolean isExtendedAPDULengthSupported() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      return isoDep.isExtendedLengthApduSupported();
    }

    int maxTranceiveLength = isoDep.getMaxTransceiveLength();
    return maxTranceiveLength > 261;
  }

  /**
   * Closes the session with the card.
   */
  public void close() {
    try {
      isoDep.close();
      state = SESSION_STOPPED_STATE;
    } catch (IOException e) {
      /* Disconnect failed? Fine... */
    }
  }

  /**
   * Determines whether an exception indicates a tag is lost event.
   *
   * @param e an exception
   *
   * @return whether the exception indicates a tag is lost event
   */
  public boolean isConnectionLost(Exception e) {
    if (isDirectConnectionLost(e)) {
      return true;
    }

    if (e == null) {
      return false;
    }

    Throwable cause = null;
    Throwable rootCause = e;

    while (null != (cause = rootCause.getCause()) && (rootCause != cause)) {
      rootCause = cause;
      if (isDirectConnectionLost(rootCause)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Determines whether an exception indicates a tag is lost event.
   *
   * @param e an exception
   *
   * @return whether the exception indicates a tag is lost event
   */
  private boolean isDirectConnectionLost(Throwable e) {
    if (!isoDep.isConnected()) {
      /* Connection lost, independent from precise exception. */
      return true;
    }

    if (e == null) {
      return false;
    }

    String exceptionClassName = e.getClass().getName();

    if (exceptionClassName != null && exceptionClassName.contains("TagLostException")) {
      /* Exception is an Android TagLostException. */
      return true;
    }

    String message = e.getMessage();
    if (message == null) {
      message = "";
    }

    if (message.toLowerCase().contains("tag was lost")) {
      /* Exception is likely caused by an Android TagLostException. */
      return true;
    }

    return false;
  }
}
