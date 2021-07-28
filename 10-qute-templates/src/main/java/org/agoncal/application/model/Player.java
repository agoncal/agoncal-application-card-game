package org.agoncal.application.model;
/*
 * Player class.
 */

import com.oblac.nomen.Casing;
import com.oblac.nomen.Nomen;

import java.util.LinkedList;

public class Player {

  // ======================================
  // =             Attributes             =
  // ======================================

  private String name;
  private LinkedList<Card> hand = new LinkedList<>(); // Cards in hand

  public static final String RANDOM_PLAYER_NAME_ONE = Nomen.est().superhero().withCasing(Casing.CAPITALIZE).get();
  public static final String RANDOM_PLAYER_NAME_TWO = Nomen.est().superhero().withCasing(Casing.CAPITALIZE).get();


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
