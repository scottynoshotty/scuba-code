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

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Inputstream for reading files on ISO 7816 file system cards.
 *
 * @author Martijn Oostdijk (martijn.oostdijk@gmail.com)
 */
public class CardFileInputStream extends InputStream {

  private static final Logger LOGGER = Logger.getLogger("net.sf.scuba");

  private FileInfo[] path;
  private final byte[] buffer;
  private int bufferLength;
  private int offsetBufferInFile;
  private int offsetInBuffer;
  private int markedOffset;
  private int fileLength;
  private FileSystemStructured fs;

  /**
   * An input stream for reading from the currently selected file in the indicated file system.
   *
   * @param maxBlockSize maximum block size to use for read binaries
   * @param fs the file system
   *
   * @throws CardServiceException on error
   */
  public CardFileInputStream(int maxBlockSize, FileSystemStructured fs) throws CardServiceException {
    this.fs = fs;
    synchronized(this.fs) {
      FileInfo[] fsPath = fs.getSelectedPath();
      if (fsPath == null || fsPath.length < 1) {
        throw new CardServiceException("No valid file selected, path = " + Arrays.toString(fsPath));
      }
      this.path = new FileInfo[fsPath.length];
      System.arraycopy(fsPath, 0, this.path, 0, fsPath.length);
      fileLength = fsPath[fsPath.length - 1].getFileLength();
      buffer = new byte[maxBlockSize];
      bufferLength = 0;
      offsetBufferInFile = 0;
      offsetInBuffer = 0;
      markedOffset = -1;
    }
  }

  @Override
  public int read() throws IOException {
    synchronized(fs) {
      try {
        if (!Arrays.equals(path, fs.getSelectedPath())) {
          for (FileInfo fileInfo: path) {
            fs.selectFile(fileInfo.getFID());
          }
        }
      } catch (CardServiceException cse) {
        /* ERROR: selecting proper path failed. */
        throw new IOException("Unexpected exception", cse); // FIXME: proper error handling here
      }

      int offsetInFile = offsetBufferInFile + offsetInBuffer;
      if (offsetInFile >= fileLength) {
        return -1;
      }
      if (offsetInBuffer >= bufferLength) {
        int le = Math.min(buffer.length, fileLength - offsetInFile);
        try {
          /* NOTE: using tmp variables here, in case fill throws an exception (which we don't catch). */
          int newOffsetBufferInFile = offsetBufferInFile + bufferLength;
          int newOffsetInBuffer = 0;
          int newBufferLength = 0;
          while (newBufferLength == 0) {
            newBufferLength = fillBufferFromFile(path, newOffsetBufferInFile, le);
          }
          offsetBufferInFile = newOffsetBufferInFile;
          offsetInBuffer = newOffsetInBuffer;
          bufferLength = newBufferLength;
        } catch (CardServiceException cse) {
          throw new IOException("Unexpected exception", cse);
        } catch (Exception e) {
          throw new IOException("Unexpected exception", e);
        }
      }
      int result = buffer[offsetInBuffer] & 0xFF;
      offsetInBuffer++;
      return result;
    }
  }

  @Override
  public long skip(long n) {
    synchronized(fs) {
      if (n < (bufferLength - offsetInBuffer)) {
        offsetInBuffer += n;
      } else {
        int offsetInFile = offsetBufferInFile + offsetInBuffer;
        offsetBufferInFile = (int) (offsetInFile + n); /* FIXME: shouldn't we check for EOF? We know fileLength... */
        offsetInBuffer = 0;
        bufferLength = 0;
        offsetInFile = offsetBufferInFile + offsetInBuffer;
      }
      return n;
    }
  }

  @Override
  public synchronized int available() {
    return bufferLength - offsetInBuffer;
  }

  @Override
  public void mark(int readLimit) {
    synchronized(fs) {
      markedOffset = offsetBufferInFile + offsetInBuffer;
    }
  }

  @Override
  public void reset() throws IOException {
    synchronized(fs) {
      if (markedOffset < 0) {
        throw new IOException("Mark not set");
      }
      offsetBufferInFile = markedOffset;
      offsetInBuffer = 0;
      bufferLength = 0;
    }
  }

  @Override
  public boolean markSupported() {
    synchronized(fs) {
      return true;
    }
  }

  /**
   * Gets the length of the underlying card file.
   *
   * @return the length of the underlying card file.
   */
  public int getLength() {
    return fileLength;
  }

  public int getPostion() {
    return offsetBufferInFile + offsetInBuffer;
  }

  /**
   * Reads from file with id <code>fid</code>.
   *
   * @param fid the file to read
   * @param offsetInFile starting offset in file
   * @param length the number of bytes to read, or -1 to read until EOF
   *
   * @return the number of bytes that were actually buffered (at most {@code le})
   */
  private int fillBufferFromFile(FileInfo[] path, int offsetInFile, int le) throws CardServiceException {
    synchronized(fs) {
      if (le > buffer.length) {
        throw new IllegalArgumentException("length too big");
      }
      if (!Arrays.equals(fs.getSelectedPath(), path)) {
        for (FileInfo fileInfo: path) {
          fs.selectFile(fileInfo.getFID());
        }
      }
      byte[] data = fs.readBinary(offsetInFile, le);
      if (data == null) {
        return 0;
      }

      System.arraycopy(data, 0, buffer, 0, data.length);
      return data.length;
    }
  }
}
