package org.agoncal.application.model;

/*
 * A card class.
 */

import static org.agoncal.application.model.Suit.SPADES;

public class Card {

  // ======================================
  // =             Attributes             =
  // ======================================

  private final Suit suit; // 1 Clubs, 2 Diamonds, 3 Hearts, 4 Spades
  private final int value; // 1 Ace... 11 J, 12 Q, 13 K

  // ======================================
  // =            Constructors            =
  // ======================================

  public Card() {
    this.suit = SPADES;
    this.value = 1;
  }

  public Card(Suit suit, int value) {
    this.suit = suit;
    this.value = value;
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public Suit getSuit() {
    return this.suit;
  }

  public String getName() {
    String name;

    // Convert int value to name of face value
    if (this.value == 1)
      name = "A";
    else if (value == 11)
      name = "J";
    else if (value == 12)
      name = "Q";
    else if (value == 13)
      name = "K";
    else // For cards 2 through 10
      name = Integer.toString(value);

    return name;
  }

  public String getCode() {
    return getName() + suit.getCode();
  }

  public String getImage() {
    return "https://deckofcardsapi.com/static/img/" + getCode() + ".png";
  }

  public String toString() {
    return getName() + suit.getAscii();
  }
}
