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

package net.sf.scuba.tlv;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An input-stream for parsing TLV structures that wraps an existing input-stream.
 *
 * @author Martijn Oostdijk (martijn.oostdijk@gmail.com)
 *
 * @version $Revision$
 */
public class TLVInputStream extends InputStream {

  private static final Logger LOGGER = Logger.getLogger("net.sf.scuba.tlv");

  /* 64K ought to be enough for anybody. */
  private static final int MAX_BUFFER_LENGTH = 65535; // Integer.MAX_VALUE;

  private final InputStream originalInputStream;

  /** Carrier. */
  private DataInputStream inputStream;

  private int bufferSize;

  private TLVInputState state;
  private TLVInputState markedState;

  /**
   * Constructs a new TLV stream based on another stream.
   *
   * @param inputStream a TLV object
   */
  public TLVInputStream(InputStream inputStream) {
    this.bufferSize = 0;
    try {
      if (inputStream instanceof BufferedInputStream || inputStream instanceof ByteArrayInputStream) {
        this.bufferSize = inputStream.available();
      }
    } catch (IOException ioe) {
      /* NOTE: if available fails, we leave buffer size at 0. */
      LOGGER.log(Level.WARNING, "Exception reading from stream", ioe);
    }
    this.originalInputStream = inputStream;
    this.inputStream = inputStream instanceof DataInputStream ? (DataInputStream)inputStream : new DataInputStream(inputStream);
    state = new TLVInputState();

    markedState = null;
  }

  /**
   * Reads a tag.
   *
   * @return the tag just read
   *
   * @throws IOException if reading goes wrong
   */
  public int readTag() throws IOException {
    if (!state.isAtStartOfTag() && !state.isProcessingValue()) {
      throw new IllegalStateException("Not at start of tag");
    }
    int tag = -1;
    int bytesRead = 0;
    try {
      int b = inputStream.readUnsignedByte();
      bytesRead++;
      while (b == 0x00 || b == 0xFF) {
        b = inputStream.readUnsignedByte();
        bytesRead++; /* skip 00 and FF */
      }
      switch (b & 0x1F) {
        case 0x1F:
          tag = b; /* We store the first byte including LHS nibble */
          b = inputStream.readUnsignedByte();
          bytesRead++;
          while ((b & 0x80) == 0x80) {
            tag <<= 8;
            tag |= (b & 0x7F);
            b = inputStream.readUnsignedByte();
            bytesRead++;
          }
          tag <<= 8;
          tag |= (b & 0x7F);
          /*
           * Byte with MSB set is last byte of
           * tag...
           */
          break;
        default:
          tag = b;
          break;
      }
      state.setTagProcessed(tag, bytesRead);
      return tag;
    } catch (IOException e) {
      throw e;
    }
  }

  /**
   * Reads a length.
   *
   * @return the length just read
   *
   * @throws IOException if reading goes wrong
   */
  public int readLength() throws IOException {
    try {
      if (!state.isAtStartOfLength()) {
        throw new IllegalStateException("Not at start of length");
      }
      int bytesRead = 0;
      int length = 0;
      int b = inputStream.readUnsignedByte();
      bytesRead++;
      if ((b & 0x80) == 0x00) {
        /* short form */
        length = b;
      } else {
        /* long form */
        int count = b & 0x7F;
        length = 0;
        for (int i = 0; i < count; i++) {
          b = inputStream.readUnsignedByte();
          bytesRead++;
          length <<= 8;
          length |= b;
        }
      }
      state.setLengthProcessed(length, bytesRead);

      /* We're buffering our carrier inputstream now that we know max length */
      //			if (bufferSize < length && length < MAX_BUFFER_LENGTH) {
      //				bufferSize = length;
      //				this.inputStream = new DataInputStream(new BufferedInputStream(originalInputStream, bufferSize));
      //			}

      return length;
    } catch (IOException e) {
      throw e;
    }
  }

  /**
   * Reads a value.
   *
   * @return the value just read
   *
   * @throws IOException if reading goes wrong
   */
  public byte[] readValue() throws IOException {
    try {
      if (!state.isProcessingValue()) {
        throw new IllegalStateException("Not yet processing value!");
      }
      int length = state.getLength();
      byte[] value = new byte[length];
      inputStream.readFully(value);
      state.updateValueBytesProcessed(length);
      return value;
    } catch (IOException e) {
      throw e;
    }
  }

  private long skipValue() throws IOException {
    if (state.isAtStartOfTag()) {
      return 0;
    }
    if (state.isAtStartOfLength()) {
      return 0;
    }
    int bytesLeft = state.getValueBytesLeft();
    return skip(bytesLeft);
  }

  /**
   * Skips in this stream until a given tag is found (depth first).
   * The stream is positioned right after the first occurrence of the tag.
   *
   * @param searchTag the tag to search for
   *
   * @throws IOException on error
   */
  public void skipToTag(int searchTag) throws IOException {
    while (true) {
      /* Get the next tag. */
      int tag = -1;
      if (state.isAtStartOfTag()) {
        /* Nothing. */
      } else if (state.isAtStartOfLength()) {
        readLength();
        if (TLVUtil.isPrimitive(state.getTag())) {
          skipValue();
        }
      } else {
        if (TLVUtil.isPrimitive(state.getTag())) {
          skipValue();
        }

      }
      tag = readTag();
      if (tag == searchTag) {
        return;
      }

      if (TLVUtil.isPrimitive(tag)) {
        int length = readLength();
        int skippedBytes = (int) skipValue();
        if (skippedBytes >= length) {
          /* Now at next tag. */
          continue;
        } else {
          /* Could only skip less than length bytes,
           * we're lost, probably at EOF. */
          break;
        }
      }
    }
  }

  /**
   * Returns an estimate of the number of bytes that can be read (or
   * skipped over) from this input-stream without blocking by the next
   * invocation of a method for this input-stream.
   *
   * @return a number of bytes
   *
   * @throws IOException if something goes wrong
   */
  @Override
  public int available() throws IOException {
    return inputStream.available();
  }

  /**
   * Reads the next byte of data from the input-stream.
   *
   * @return a byte
   *
   * @throws IOException if reading goes wrong
   */
  @Override
  public int read() throws IOException {
    int result = inputStream.read();
    if (result < 0) {
      return -1;
    }
    state.updateValueBytesProcessed(1);
    return result;
  }

  /**
   * Attempts to skip over <code>n</code> bytes.
   *
   * @return the actual number of bytes skipped
   *
   * @throws IOException if something goes wrong
   */
  @Override
  public long skip(long n) throws IOException {
    if (n <= 0) {
      return 0;
    }
    long result = inputStream.skip(n);
    state.updateValueBytesProcessed((int) result);
    return result;
  }

  /**
   * Marks the underlying input-stream if supported.
   *
   * @param readLimit limit for marking
   */
  @Override
  public synchronized void mark(int readLimit) {
    inputStream.mark(readLimit);
    markedState = new TLVInputState(state);
  }

  /**
   * Whether marking and resetting are supported.
   * We support this whenever the underlying input-stream supports it.
   *
   * @return whether mark and reset are supported
   */
  @Override
  public boolean markSupported() {
    return inputStream.markSupported();
  }

  /**
   * Resets the underlying input-stream if supported.
   *
   * @throws IOException if something goes wrong
   */
  @Override
  public synchronized void reset() throws IOException {
    if (!markSupported()) {
      throw new IOException("mark/reset not supported");
    }
    inputStream.reset();
    state = markedState;
    markedState = null;
  }

  /**
   * Closes this input-stream.
   *
   * @throws IOException if something goes wrong
   */
  @Override
  public void close() throws IOException {
    inputStream.close();
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
