package org.agoncal.application.model;
/*
 * A deck of cards.
 */

import javax.json.bind.annotation.JsonbProperty;
import java.util.LinkedList;

public class Deck {

  // ======================================
  // =             Attributes             =
  // ======================================

  @JsonbProperty("deck_id")
  private String id;
  private Integer remaining;
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

  public Integer getRemaining() {
    return remaining == null ? cards.size() : remaining;
  }

  public void setRemaining(Integer remaining) {
    this.remaining = remaining;
  }

  public LinkedList<Card> getCards() {
    return cards;
  }

  public void setCards(LinkedList<Card> cards) {
    this.cards = cards;
  }
}
