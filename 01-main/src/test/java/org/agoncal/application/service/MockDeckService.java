package org.agoncal.application.service;

import org.agoncal.application.model.Card;
import org.agoncal.application.model.Deck;

import java.util.Collections;
import java.util.LinkedList;

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.agoncal.application.model.Suit.HEARTS;
import static org.agoncal.application.model.Suit.SPADES;

public class MockDeckService implements DeckService {

  @Override
  public Deck getNewShuffledDeck() {
    LinkedList<Card> cards = new LinkedList<>();
    for (int rank = 0; rank <= 12; rank++) {
      cards.add(new Card(rank, CLUBS));
      cards.add(new Card(rank, DIAMONDS));
      cards.add(new Card(rank, HEARTS));
      cards.add(new Card(rank, SPADES));
    }
    Collections.shuffle(cards);
    return new Deck(cards);
  }

  @Override
  public Deck dealOneCard(String deckid) {
    return null;
  }
}
