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


/**
 * This class implements some basic file selection / reading / writing
 * routines from the  ISO7816-4 standard.
 *
 * TODO: Work in heavy progress
 *
 * @author woj
 *
 * @version $Revision$
 */
public abstract class AbstractFileSystemStructured implements FileSystemStructured {

  public static final short MF_ID = 0x3F00;

  private CardService service = null;
  private short selectedFID = 0;
  private int length = -1;
  private int p2 = 0;
  private int selectLe = 256;
  private ISOFileInfo fileInfo = null;

  public AbstractFileSystemStructured(CardService service) {
    this.service = service;
  }

  public AbstractFileSystemStructured(CardService service, boolean fileInfo) {
    this.service = service;
    this.p2 = fileInfo ? 0x00 : 0x0C;
    this.selectLe = fileInfo ? 256 : 0;
  }

  public int getFileLength() throws CardServiceException {
    return length;
  }

  public short getSelectedFID() {
    return selectedFID;
  }

  public abstract byte[] readBinary(int offset, int length);

  private void selectFile(byte[] data, int p1) throws CardServiceException {
    CommandAPDU command = createSelectFileAPDU(p1, p2, data, selectLe);
    ResponseAPDU response = service.transmit(command);

    int respSW = response.getSW();
    byte[] respData = response.getData();

    if( respSW != ISO7816.SW_NO_ERROR) {
      throw new CardServiceException("File could not be selected.", respSW);
    }
    // store selected fid:
    // 0, 4, 8 absolute
    // 1, 2, 9, relative
    // 3 parent
    this.fileInfo = new ISOFileInfo( respData );
    if(this.fileInfo.fid != -1) {
      selectedFID = this.fileInfo.fid;
    }
    if(this.fileInfo.fileLength != -1) {
      length = this.fileInfo.fileLength;
    }
  }

  private void selectFile(short fid, int p1) throws CardServiceException {
    byte[] fidbytes = (fid == 0) ? new byte[0] : new byte[]{ (byte) ((fid >> 8) & 0x000000FF), (byte) (fid & 0x000000FF) };
    selectFile(fidbytes, p1);
  }

  public void selectFile(short fid) throws CardServiceException {
    selectFile(fid, 0x00);
  }

  public void selectMF() throws CardServiceException {
    selectFile((short)0, 0);
  }

  public void selectParent() throws CardServiceException {
    selectFile((short)0, 0x03);
  }

  public void selectEFRelative(short fid) throws CardServiceException {
    selectFile(fid, 0x02);
  }

  public void selectDFRelative(short fid) throws CardServiceException {
    selectFile(fid, 0x01);
  }

  public void selectAID(byte[] aid) throws CardServiceException {
    selectFile(aid, 0x04);
  }

  public void selectPath(byte[] path) throws CardServiceException {
    selectFile(path, 0x08);
  }

  public void selectPathRelative(byte[] path) throws CardServiceException {
    selectFile(path, 0x09);
  }

  private CommandAPDU createSelectFileAPDU(int p1, int p2, byte[] data, int le) {
    if( le == 0) {
      return new CommandAPDU( ISO7816.CLA_ISO7816, ISO7816.INS_SELECT_FILE, p1, p2, data );
    //return new CommandAPDU(ISO7816.CLA_ISO7816, ISO7816.INS_SELECT_FILE, p1, p2, data);
    } else {
      //return new CommandAPDU(ISO7816.CLA_ISO7816, ISO7816.INS_SELECT_FILE, p1, p2, data, le);
      return new CommandAPDU(ISO7816.CLA_ISO7816, ISO7816.INS_SELECT_FILE, p1, p2, data, le);
    }
  }

}
