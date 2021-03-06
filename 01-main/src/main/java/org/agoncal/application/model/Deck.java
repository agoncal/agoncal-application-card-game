package org.agoncal.application.model;
/*
 * A deck of cards.
 */

import java.util.LinkedList;

public class Deck {

  // ======================================
  // =             Attributes             =
  // ======================================

  private String id;
  private int remaining;
  private LinkedList<Card> cards = new LinkedList<>();

  public static final int NUMBER_OF_CARDS = 52;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Deck() {
  }

  public Deck(String id) {
    this.id = id;
  }

  public Deck(LinkedList<Card> cards) {
    this.cards = cards;
  }

  // ======================================
  // =              Methods               =
  // ======================================

  // Deals one card at a time
  public Card dealOneCard() {
    return cards.removeFirst();
  }

  // ======================================
  // =        Getters and Setters         =
  // ======================================

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getRemaining() {
    return remaining;
  }

  public void setRemaining(int remaining) {
    this.remaining = remaining;
  }

  public LinkedList<Card> getCards() {
    return cards;
  }

  public void setCards(LinkedList<Card> cards) {
    this.cards = cards;
  }
}
