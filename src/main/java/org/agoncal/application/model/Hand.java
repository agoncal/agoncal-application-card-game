package org.agoncal.application.model;
/*
 * Hand.java
 */

import java.util.LinkedList;

public class Hand {

  // Attributes

  private LinkedList<Card> cards = new LinkedList<>(); // Cards in hand

  // Methods

  public void addCard(Card card) {
    cards.add(card);
  }

  public Card playCard() {
    return cards.removeFirst();
  }

  public int getHandSize() {
    return cards.size();
  }

  public LinkedList<Card> getCards() {
    return cards;
  }
}
