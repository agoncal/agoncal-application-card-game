package org.agoncal.application.model;

/*
 * A card class.
 */

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.agoncal.application.model.Suit.HEARTS;
import static org.agoncal.application.model.Suit.SPADES;

public class Card {

  // ======================================
  // =             Attributes             =
  // ======================================

  private Suit suit; // 1 Clubs, 2 Diamonds, 3 Hearts, 4 Spades
  private int value; // 1 Ace... 11 Jack, 12 Queen, 13 King
  private String code; // QC, JD, 2H
  private String image; // URL of the PNG

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

  public Card(String suit, String name) {
    setSuit(suit);
    setValue(name);
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
      name = "ACE";
    else if (value == 11)
      name = "JACK";
    else if (value == 12)
      name = "QUEEN";
    else if (value == 13)
      name = "KING";
    else // For cards 2 through 10
      name = Integer.toString(value);

    return name;
  }

  public void setValue(String name) {
    // Convert int value to name of face value
    if (name.equals("ACE"))
      this.value = 1;
    else if (name.equals("JACK"))
      this.value = 11;
    else if (name.equals("QUEEN"))
      this.value = 12;
    else if (name.equals("KING"))
      this.value = 13;
    else // For cards 2 through 10
      this.value = Integer.parseInt(name);
  }

  public void setSuit(String name) {
    // Convert int value to name of face value
    if (name.equals("CLUBS"))
      this.suit = CLUBS;
    else if (name.equals("DIAMONDS"))
      this.suit = DIAMONDS;
    else if (name.equals("HEARTS"))
      this.suit = HEARTS;
    else if (name.equals("SPADES"))
      this.suit = SPADES;

  }

  public int getValue() {
    return value;
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

  public void setImage(String image) {
    this.image = image;
  }
}
