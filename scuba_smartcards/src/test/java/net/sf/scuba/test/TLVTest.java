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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.tlv.ASN1Constants;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVOutputStream;

public class TLVTest extends TestCase {

  private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");

  public void testSimpleTLV() {
    int constantTag = 0x30;
    testSimpleTLV(constantTag, new byte[] { 0x01, 0x02, 0x03, 0x04, 0x05 });

    int runCount = 23;

    for (int i = 0; i < runCount; i++) {
      testSimpleRandomValuesTLV(constantTag);

      /* Typical ASN1 tags. */
      testSimpleRandomValuesTLV(ASN1Constants.BIT_STRING_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.BMP_STRING_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.BOOLEAN_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.EMBEDDED_PDV_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.ENUMERATED_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.EXTERNAL_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.GENERAL_STRING_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.GENERALIZED_TIME_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.GRAPHIC_STRING_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.IA5_STRING_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.INTEGER_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.NULL_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.NUMERIC_STRING_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.OBJECT_DESCRIPTOR_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.OBJECT_IDENTIFIER_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.OCTET_STRING_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.PRINTABLE_STRING_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.REAL_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.SEQUENCE_TYPE_TAG);
      testSimpleRandomValuesTLV(ASN1Constants.SET_TYPE_TAG);

      /* Some ISO 7816-4 tags. */
      testSimpleRandomValuesTLV(ISO7816.TAG_SM_ENCRYPTED_DATA);
      testSimpleRandomValuesTLV(ISO7816.TAG_SM_ENCRYPTED_DATA_WITH_PADDING_INDICATOR);
      testSimpleRandomValuesTLV(ISO7816.TAG_SM_CRYPTOGRAPHIC_CHECKSUM);
      testSimpleRandomValuesTLV(ISO7816.TAG_SM_EXPECTED_LENGTH);
      testSimpleRandomValuesTLV(ISO7816.TAG_SM_STATUS_WORD);
      
      /* Not valid tags? */
      //  testSimpleRandomValuesTLV(0x00);
      //  testSimpleRandomValuesTLV(0x1F);
      //  testSimpleRandomValuesTLV(0x3F);
      //  testSimpleRandomValuesTLV(0x5F);
      //  testSimpleRandomValuesTLV(0x7F);
      //  testSimpleRandomValuesTLV(0x9F);
      //  testSimpleRandomValuesTLV(0xBF);
      //  testSimpleRandomValuesTLV(0xDF);
      //  testSimpleRandomValuesTLV(0xFF);
    }
  }  

  public void testSimpleRandomValuesTLV(int tag) {

    Random random = new Random();

    byte[] valueOfLength255 = new byte[255];
    random.nextBytes(valueOfLength255);
    testSimpleTLV(tag, valueOfLength255);

    byte[] valueOfLength256 = new byte[256];
    random.nextBytes(valueOfLength256);
    testSimpleTLV(tag, valueOfLength256);

    byte[] valueOfLength1024 = new byte[1024];
    random.nextBytes(valueOfLength1024);
    testSimpleTLV(tag, valueOfLength1024);

    byte[] valueOfLength65535 = new byte[65535];
    random.nextBytes(valueOfLength65535);
    testSimpleTLV(tag, valueOfLength65535);

    byte[] valueOfLength65536 = new byte[65536];
    random.nextBytes(valueOfLength65536);
    testSimpleTLV(tag, valueOfLength65536);
  }

  public void testSimpleTLV(int tag, byte[] value) {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      TLVOutputStream tlvOutputStream = new TLVOutputStream(byteArrayOutputStream);

      try {
        tlvOutputStream.writeTag(tag);
        tlvOutputStream.writeValue(value);
      } finally {
        tlvOutputStream.close();
      }

      byte[] encoded = byteArrayOutputStream.toByteArray();

      TLVInputStream tlvInputStream = new TLVInputStream(new ByteArrayInputStream(encoded));
      try {
        int actualTag = tlvInputStream.readTag();
        assertEquals(tag, actualTag);
        int actualLength = tlvInputStream.readLength();
        assertEquals(value.length, actualLength);
        byte[] actualValue = tlvInputStream.readValue();
        assertTrue(Arrays.equals(value, actualValue));
      } finally {
        tlvInputStream.close();
      }
    } catch (Exception e) {
      LOGGER.log(Level.WARNING, "Exception", e);
      fail(e.getMessage());
    }
  }
  
  public void testSimpleTLVStreamWrite(int tag, byte[] value) {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      TLVOutputStream tlvOutputStream = new TLVOutputStream(byteArrayOutputStream);

      try {
        tlvOutputStream.writeTag(tag);
        
        Random random = new Random();
        int firstLength = random.nextInt(Math.max(0, value.length - 1));
        int secondLength = value.length - firstLength;
        byte[] first = new byte[firstLength];
        byte[] second = new byte[secondLength];
        System.arraycopy(value, 0, first, 0, firstLength);
        System.arraycopy(value, 0, second, 0, secondLength);
        tlvOutputStream.write(first);
        tlvOutputStream.write(second);
        tlvOutputStream.writeValueEnd();
      } finally {
        tlvOutputStream.close();
      }

      byte[] encoded = byteArrayOutputStream.toByteArray();

      TLVInputStream tlvInputStream = new TLVInputStream(new ByteArrayInputStream(encoded));
      try {
        int actualTag = tlvInputStream.readTag();
        assertEquals(tag, actualTag);
        int actualLength = tlvInputStream.readLength();
        assertEquals(value.length, actualLength);
        byte[] actualValue = tlvInputStream.readValue();
        assertTrue(Arrays.equals(value, actualValue));
      } finally {
        tlvInputStream.close();
      }
    } catch (Exception e) {
      LOGGER.log(Level.WARNING, "Exception", e);
      fail(e.getMessage());
    }
  }

  public void testComplexTLV() {
    int level1Tag = 0x30;
    int level2FirstTag = 0x32;

    Random random = new Random();

    int runCount = 17;
    for (int i = 0; i < runCount; i++) {
      byte[] firstValue = new byte[1025];
      random.nextBytes(firstValue);

      byte[] secondValue = new byte[1023];
      random.nextBytes(secondValue);

      int level2SecondTag = 0x34;
      testComplexTLV(level1Tag, level2FirstTag, level2SecondTag, firstValue, secondValue);
    }
  }

  public void testComplexTLV(int level1Tag, int level2FirstTag, int level2SecondTag, byte[] firstValue, byte[] secondValue) {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      TLVOutputStream tlvOutputStream = new TLVOutputStream(byteArrayOutputStream);

      try {
        tlvOutputStream.writeTag(level1Tag);
        tlvOutputStream.writeTag(level2FirstTag);
        tlvOutputStream.writeValue(firstValue);
        tlvOutputStream.flush();
        tlvOutputStream.writeTag(level2SecondTag);
        tlvOutputStream.writeValue(secondValue);
        tlvOutputStream.writeValueEnd();
        tlvOutputStream.flush();
      } finally {
        tlvOutputStream.close();
      }

      byte[] encoded = byteArrayOutputStream.toByteArray();

      TLVInputStream tlvInputStream = new TLVInputStream(new ByteArrayInputStream(encoded));
      try {
        int actualLevel1Tag = tlvInputStream.readTag();
        assertEquals(level1Tag, actualLevel1Tag);
        int level1Length = tlvInputStream.readLength();
        assertTrue(level1Length >= firstValue.length + secondValue.length);

        int actualLevel2FirstTag = tlvInputStream.readTag();
        assertEquals(level2FirstTag, actualLevel2FirstTag);
        int actualLevel2FirstLength = tlvInputStream.readLength();
        assertEquals(firstValue.length, actualLevel2FirstLength);
        byte[] actualFirstValue = tlvInputStream.readValue();
        assertTrue(Arrays.equals(firstValue, actualFirstValue));

        int actualLevel2SecondTag = tlvInputStream.readTag();
        assertEquals(level2SecondTag, actualLevel2SecondTag);
        int actualLevel2SecondLength = tlvInputStream.readLength();
        assertEquals(secondValue.length, actualLevel2SecondLength);
        byte[] actualSecondValue = tlvInputStream.readValue();
        assertTrue(Arrays.equals(secondValue, actualSecondValue));   
      } finally {
        tlvInputStream.close();
      }
    } catch (Exception e) {
      LOGGER.log(Level.WARNING, "Exception", e);
      fail(e.getMessage());
    }
  }
}
