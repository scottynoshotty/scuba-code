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

package net.sf.scuba.tlv;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * State keeps track of where we are in a TLV stream.
 *
 * @author The SCUBA team
 *
 * @version $Revision$
 */
class TLVInputState {

  /**
   * Encodes tags, lengths, and number of valueBytes encountered thus far.
   */
  private Deque<TLStruct> state;

  /*
   * TFF: ^TLVVVVVV
   * FTF: T^LVVVVVV
   * FFT: TL^VVVVVV
   * FFT: TLVVVV^VV
   * TFF: ^
   */
  private boolean isAtStartOfTag;
  private boolean isAtStartOfLength;
  private boolean isReadingValue;

  public TLVInputState() {
    this(new ArrayDeque<TLStruct>(), true, false, false);
  }

  public TLVInputState(TLVInputState original) {
    this(original.getDeepCopyOfState(), original.isAtStartOfTag, original.isAtStartOfLength, original.isReadingValue);
  }
  
  private TLVInputState(Deque<TLStruct> state, boolean isAtStartOfTag, boolean isAtStartOfLength, boolean isReadingValue) {
    this.state = state;
    this.isAtStartOfTag = isAtStartOfTag;
    this.isAtStartOfLength = isAtStartOfLength;
    this.isReadingValue = isReadingValue;
  }

  public boolean isAtStartOfTag() {
    return isAtStartOfTag;
  }

  public boolean isAtStartOfLength() {
    return isAtStartOfLength;
  }

  public boolean isProcessingValue() {
    return isReadingValue;
  }

  public int getTag() {
    if (state.isEmpty()) {
      throw new IllegalStateException("Tag not yet read.");
    }
    TLStruct currentObject = state.peek();
    return currentObject.getTag();
  }

  public int getLength() {
    if (state.isEmpty()) {
      throw new IllegalStateException("Length not yet known.");
    }
    TLStruct currentObject = state.peek();
    return currentObject.getLength();
  }

  public int getValueBytesProcessed() {
    TLStruct currentObject = state.peek();
    return currentObject.getValueBytesProcessed();
  }

  public int getValueBytesLeft() {
    if (state.isEmpty()) {
      throw new IllegalStateException("Length of value is unknown.");
    }
    TLStruct currentObject = state.peek();
    int currentLength = currentObject.getLength();
    int valueBytesRead = currentObject.getValueBytesProcessed();
    return currentLength - valueBytesRead;
  }

  public void setTagProcessed(int tag, int byteCount) {
    /* Length is set to MAX INT, we will update it when caller calls our setLengthProcessed. */
    TLStruct obj = new TLStruct(tag);
    if (!state.isEmpty()) {
      TLStruct parent = state.peek();
      parent.updateValueBytesProcessed(byteCount);
    }
    state.push(obj);
    isAtStartOfTag = false;
    isAtStartOfLength = true;
    isReadingValue = false;
  }

  public void setDummyLengthProcessed() {
    isAtStartOfTag = false;
    isAtStartOfLength = false;
    isReadingValue = true;
  }

  public void setLengthProcessed(int length, int byteCount) {
    if (length < 0) {
      throw new IllegalArgumentException("Cannot set negative length (length = " + length + ", 0x" + Integer.toHexString(length) + " for tag " + Integer.toHexString(getTag()) + ").");
    }
    TLStruct obj = state.pop();
    if (!state.isEmpty()) {
      TLStruct parent = state.peek();
      parent.updateValueBytesProcessed(byteCount);
    }
    obj.setLength(length);
    state.push(obj);
    isAtStartOfTag = false;
    isAtStartOfLength = false;
    isReadingValue = true;
  }

  public void updateValueBytesProcessed(int byteCount) {
    if (state.isEmpty()) {
      return;
    }
    TLStruct currentObject = state.peek();
    int bytesLeft = currentObject.getLength() - currentObject.getValueBytesProcessed();
    if (byteCount > bytesLeft) {
      throw new IllegalArgumentException("Cannot process " + byteCount + " bytes! Only " + bytesLeft + " bytes left in this TLV object " + currentObject);
    }
    currentObject.updateValueBytesProcessed(byteCount);
    int currentLength = currentObject.getLength();
    if (currentObject.getValueBytesProcessed() == currentLength) {
      state.pop();
      /* Stand back! I'm going to try recursion! Update parent(s)... */
      updateValueBytesProcessed(currentLength);
      isAtStartOfTag = true;
      isAtStartOfLength = false;
      isReadingValue = false;
    } else {
      isAtStartOfTag = false;
      isAtStartOfLength = false;
      isReadingValue = true;
    }
  }

  @Override
  public String toString() {
    return state.toString();
  }

  private Deque<TLStruct> getDeepCopyOfState() {
    Deque<TLStruct> newStack = new ArrayDeque<TLStruct>(state.size());
    for (TLStruct tlStruct: state) {
      newStack.addLast(new TLStruct(tlStruct));
    }
    return newStack;
  }

  private class TLStruct {

    private int tag;
    private int length;
    private int valueBytesRead;

    public TLStruct(int tag) {
      this(tag, Integer.MAX_VALUE, 0);
    }
    
    public TLStruct(TLStruct original) {
      this(original.tag, original.length, original.valueBytesRead);
    }
    
    public TLStruct(int tag, int length, int valueBytesRead) {
      this.tag = tag;
      this.length = length;
      this.valueBytesRead = valueBytesRead;
    }

    public void setLength(int length) {
      this.length = length;
    }

    public int getTag() {
      return tag;
    }

    public int getLength() {
      return length;
    }

    public int getValueBytesProcessed() {
      return valueBytesRead;
    }

    public void updateValueBytesProcessed(int n) {
      this.valueBytesRead += n;
    }

    @Override
    public String toString() {
      return "[TLStruct " + Integer.toHexString(tag) + ", " + length + ", " + valueBytesRead + "]";
    }
  }
}
