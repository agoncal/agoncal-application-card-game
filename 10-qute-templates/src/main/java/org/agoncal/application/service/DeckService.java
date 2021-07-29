package org.agoncal.application.service;

import com.oblac.nomen.Nomen;
import org.agoncal.application.model.Card;
import org.agoncal.application.model.Deck;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.agoncal.application.model.Suit.HEARTS;
import static org.agoncal.application.model.Suit.SPADES;

@ApplicationScoped
public class DeckService {

  @Inject
  @RestClient
  DeckOfCards deckOfCardsProxy;

  Map<String, Deck> decks = new HashMap<>();

  @Fallback(fallbackMethod = "fallbackNewShuffledDeck")
  @Retry
  public Deck newShuffledDeck() {
    return deckOfCardsProxy.newShuffledDeck();
  }

  @Fallback(fallbackMethod = "fallbackDealOneCard")
  @Retry
  public Card dealOneCard(String deckid) {
    return deckOfCardsProxy.dealOneCard(deckid, 1).getCards().get(0);
  }

  public Deck fallbackNewShuffledDeck() {
    String deckId = Base64.getEncoder().encodeToString(Nomen.est().noun().get().getBytes());
    Deck deck = new Deck(deckId);
    deck.setCards(getSuffledCards());
    decks.put(deckId, deck);

    return deck;
  }

  public Card fallbackDealOneCard(String deckid) {
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
