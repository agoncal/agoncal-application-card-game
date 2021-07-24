package org.agoncal.application.model;
/*
 * Player class.
 */

import java.util.LinkedList;

public class Player {
  // Attributes

  private Hand hand;
  private String name;

  // Default constructor

  public Player(String name) {
    hand = new Hand(); // Instantiate new hand object
    this.name = name;
  }

  // Methods

  public Card playCard() {
    Card c = hand.playCard();
    System.out.println(String.format("%5s", name) + " plays a " + c.getName() + "!");

    return c;
  }

  public void takeCard(Card card) {
    hand.addCard(card);
  }

  public String getName() {
    return name;
  }

  public LinkedList<Card> getCards() {
    return hand.getCards();
  }

  public int getHandSize() {
    return hand.getHandSize();
  }
}
