package org.agoncal.application.model;
/*
 * Player class.
 */

import java.util.LinkedList;

public class Player {

  // ======================================
  // =             Attributes             =
  // ======================================

  private String name;
  private LinkedList<Card> cards = new LinkedList<>(); // Cards in hand

  // ======================================
  // =            Constructors            =
  // ======================================

  public Player() {
    this.name = "Bob";
  }

  public Player(String name) {
    this.name = name;
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public Card playCard() {
    return cards.removeFirst();
  }

  public void takeCard(Card card) {
    cards.add(card);
  }

  public String getName() {
    return name;
  }

  public LinkedList<Card> getCards() {
    return cards;
  }

  public int getHandSize() {
    return cards.size();
  }
}
