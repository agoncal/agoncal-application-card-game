package org.agoncal.application.model;
/*
 * A deck of cards.
 */

import javax.json.bind.annotation.JsonbProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.agoncal.application.model.Suit.HEARTS;
import static org.agoncal.application.model.Suit.SPADES;

public class Deck {

  // ======================================
  // =             Attributes             =
  // ======================================

  @JsonbProperty("deck_id")
  private String id;
  private LinkedList<Card> cards = new LinkedList<>();

  public static final int NUMBER_OF_CARDS = 52;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Deck() {
    createShuffledDeck();
    generateRandomId();
  }

  // ======================================
  // =              Methods               =
  // ======================================

  // Deals one card at a time
  public Card dealOneCard() {
    return cards.removeFirst();
  }

  public String getId() {
    return id;
  }

  public int getRemaining() {
    return cards.size();
  }

  // ======================================
  // =          Private Methods           =
  // ======================================

  private void generateRandomId() {
    Random random = new Random();
    StringBuilder randomId = new StringBuilder();
    for (int i = 0; i < 12; i++) {
      randomId.append((char)(random.nextInt(26) + 'a'));
    }
    this.id = randomId.toString();
  }

  private void createShuffledDeck() {
    for (int rank = 0; rank <= 12; rank++) {
      cards.add(new Card(CLUBS, rank));
      cards.add(new Card(DIAMONDS, rank));
      cards.add(new Card(HEARTS, rank));
      cards.add(new Card(SPADES, rank));
    }
    Collections.shuffle(cards);
  }

  List<Card> getCards() {
    return cards;
  }
}
