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

/**
 * CardService for easy wrapping of APDU messages.
 *
 * @author Pim Vullers (pim@cs.ru.nl)
 *
 * @version $Revision$
 */
public class WrappingCardService extends CardService {

  private CardService service;
  private APDUWrapper wrapper;
  private boolean enabled;

  public WrappingCardService(CardService service, APDUWrapper wrapper) {
    this.service = service;
    this.wrapper = wrapper;
  }

  @Override
  public void open() throws CardServiceException {
    service.open();
  }

  @Override
  public boolean isOpen() {
    return service.isOpen();
  }

  @Override
  public ResponseAPDU transmit(CommandAPDU capdu)
      throws CardServiceException {
    if (isEnabled()) {
      ResponseAPDU rapdu = service.transmit(wrapper.wrap(capdu));
      return wrapper.unwrap(rapdu);
    } else {
      return service.transmit(capdu);
    }
  }

  @Override
  public byte[] getATR() throws CardServiceException {
    return service.getATR();
  }

  @Override
  public void close() {
    service.close();
  }

  public void enable() {
    enabled = true;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void disable() {
    enabled = false;
  }

  @Override
  public boolean isConnectionLost(Exception e) {
    return service.isConnectionLost(e);
  }
}
