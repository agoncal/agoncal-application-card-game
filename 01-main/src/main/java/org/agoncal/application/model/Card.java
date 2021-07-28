package org.agoncal.application.model;

/*
 * A card class.
 */

import static org.agoncal.application.model.Suit.CLUBS;

public class Card {

  // ======================================
  // =             Attributes             =
  // ======================================

  private String code; // QC, JD, 2H
  private String value; // ACE, JACK, QUEEN, KING, 2, 3
  private Suit suit; // Clubs, Diamonds, Hearts, Spades
  private String image;

  public static final String ACE_VALUE = "ACE";
  public static final String ACE_CODE = "A";
  public static final String JACK_VALUE = "JACK";
  public static final String JACK_CODE = "J";
  public static final String QUEEN_VALUE = "QUEEN";
  public static final String QUEEN_CODE = "Q";
  public static final String KING_VALUE = "KING";
  public static final String KING_CODE = "K";

  // ======================================
  // =            Constructors            =
  // ======================================

  public Card() {
    this(1, CLUBS);
  }

  public Card(String value, Suit suit) {
    this.value = value;
    this.suit = suit;
  }

  public Card(int value, Suit suit) {
    if (value == 1) {
      this.value = ACE_VALUE;
      this.code = ACE_CODE + suit.getCode();
    } else if (value == 11) {
      this.value = JACK_VALUE;
      this.code = JACK_CODE + suit.getCode();
    } else if (value == 12) {
      this.value = QUEEN_VALUE;
      this.code = QUEEN_CODE + suit.getCode();
    } else if (value == 13) {
      this.value = KING_VALUE;
      this.code = KING_CODE + suit.getCode();
    } else {
      this.code = Integer.toString(value) + suit.getCode();
      this.value = Integer.toString(value);
    }
    this.suit = suit;
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Suit getSuit() {
    return suit;
  }

  public void setSuit(Suit suit) {
    this.suit = suit;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  // ======================================
  // =              toString              =
  // ======================================

  @Override
  public String toString() {
    return code.substring(0, code.length() - 1) + suit.getAscii();
  }
}
