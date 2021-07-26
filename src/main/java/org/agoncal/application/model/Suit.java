package org.agoncal.application.model;

public enum Suit {

  // ======================================
  // =             Attributes             =
  // ======================================

  CLUBS('\u2663', 'C'), DIAMONDS('\u2666', 'D'), HEARTS('\u2764', 'H'), SPADES('\u2660', 'S');

  private final char ascii;
  private final char code;

  // ======================================
  // =            Constructors            =
  // ======================================

  Suit(char ascii, char code) {
    this.ascii = ascii;
    this.code = code;
  }

  public char getCode() {
    return code;
  }

  public char getAscii() {
    return ascii;
  }
}
