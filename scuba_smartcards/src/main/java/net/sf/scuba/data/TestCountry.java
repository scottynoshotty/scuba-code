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

package net.sf.scuba.data;

/**
 * This just defines Utopia (UT, UTO) as a special test country implementation.
 *
 * @author The SCUBA team
 *
 * @version $Revision$
 */
public class TestCountry extends Country {

  private static final long serialVersionUID = 6977914731950342700L;

  /** Utopia. */
  public static final TestCountry UT = new TestCountry(-1, "UT", "UTO", "Utopia", "Utopian");


  public static final TestCountry BP = new TestCountry(-1, "BP", "XBP", "BP", "BP");
  public static final TestCountry DV = new TestCountry(-1, "DV", "XDV", "DV", "DV");


  private int code;
  private String alpha2Code;
  private String alpha3Code;
  private String name;
  private String nationality;

  private static final TestCountry[] VALUES = { UT, BP, DV };

  private TestCountry() {
  }

  private TestCountry(int code, String alpha2Code, String alpha3Code, String name, String nationality) {
    this.code = code;
    this.alpha2Code = alpha2Code;
    this.alpha3Code = alpha3Code;
    this.name = name;
    this.nationality = nationality;
  }

  public static TestCountry[] values() {
    return VALUES;
  }

  @Override
  public int valueOf() {
    return code;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getNationality() {
    return nationality;
  }

  @Override
  public String toAlpha2Code() {
    return alpha2Code;
  }

  @Override
  public String toAlpha3Code() {
    return alpha3Code;
  }

  @Override
  public String toString() {
    return alpha2Code;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (other == this) {
      return true;
    }
    if (!(getClass().equals(other.getClass()))) {
      return false;
    }
    TestCountry otherCountry = (TestCountry)other;
    return alpha3Code.equals(otherCountry.alpha3Code);
  }

  @Override
  public int hashCode() {
    return 2 * alpha3Code.hashCode() + 31;
  }
}
