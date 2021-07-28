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
  private LinkedList<Card> hand = new LinkedList<>(); // Cards in hand

  // ======================================
  // =            Constructors            =
  // ======================================

  public Player(String name) {
    this.name = name;
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public void playCard(Card card) {
    hand.add(card);
  }

  public String getName() {
    return name;
  }

  public LinkedList<Card> getHand() {
    return hand;
  }

  public int getHandSize() {
    return hand.size();
  }
}
