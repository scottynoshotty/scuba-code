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

package net.sf.scuba.test;

import java.util.Arrays;
import java.util.Random;

import junit.framework.TestCase;
import net.sf.scuba.util.Hex;

public class HexTest extends TestCase {

  public void testHex() {
    Random random = new Random();
    byte[] bytes = new byte[1025];
    random.nextBytes(bytes);
    String hexString = Hex.bytesToHexString(bytes);
    byte[] decodedBytes = Hex.hexStringToBytes(hexString);
    assertTrue(Arrays.equals(bytes,  decodedBytes));
    
    String recodedHexString = Hex.bytesToHexString(decodedBytes);
    assertEquals(hexString, recodedHexString);
  }
}
