package org.agoncal.application.service;

import com.oblac.nomen.Nomen;
import org.agoncal.application.model.Card;
import org.agoncal.application.model.Deck;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.agoncal.application.model.Suit.HEARTS;
import static org.agoncal.application.model.Suit.SPADES;

public class DeckService {

  Map<String, Deck> decks = new HashMap<>();

  public Deck newShuffledDeck() {
    String deckId = Base64.getEncoder().encodeToString(Nomen.est().noun().get().getBytes());
    Deck deck = new Deck(deckId);
    deck.setCards(getSuffledCards());
    decks.put(deckId, deck);

    return deck;
  }

  public Card dealOneCard(String deckid) {
    if (!decks.containsKey(deckid)) {
      Deck deck = new Deck(deckid);
      deck.setCards(getSuffledCards());
      decks.put(deckid, deck);
    }

    return decks.get(deckid).dealOneCard();
  }

  private LinkedList<Card> getSuffledCards() {
    LinkedList<Card> cards = new LinkedList<>();
    for (int rank = 0; rank <= 12; rank++) {
      cards.add(new Card(rank, CLUBS));
      cards.add(new Card(rank, DIAMONDS));
      cards.add(new Card(rank, HEARTS));
      cards.add(new Card(rank, SPADES));
    }
    Collections.shuffle(cards);
    return cards;
  }
}
