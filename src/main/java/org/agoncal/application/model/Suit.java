package org.agoncal.application.model;

public enum Suit {

  // ======================================
  // =             Attributes             =
  // ======================================

  CLUBS('\u2663'), DIAMONDS('\u2666'), HEARTS('\u2764'), SPADES('\u2660');

  private final char representation;

  // ======================================
  // =            Constructors            =
  // ======================================

  Suit(char representation) {
    this.representation = representation;
  }

  @Override
  public String toString() {
    return String.valueOf(representation);
  }
}
