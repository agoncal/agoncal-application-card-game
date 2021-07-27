package org.agoncal.application.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.agoncal.application.model.Deck.NUMBER_OF_CARDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class DeckTest {

  @Test
  public void shouldCreateADeck() {
    Deck deck = new Deck();
    assertNotNull(deck.getId());
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(NUMBER_OF_CARDS, deck.getRemaining());
  }

  @Test
  public void shouldDealACard() {
    Deck deck = new Deck();
    assertNotNull(deck.getId());
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(NUMBER_OF_CARDS, deck.getRemaining());
    deck.dealOneCard();
    assertEquals(NUMBER_OF_CARDS - 1, deck.getRemaining());
  }

  @Test
  public void shouldDeal53Cards() {
    Deck deck = new Deck();
    assertNotNull(deck.getId());
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(NUMBER_OF_CARDS, deck.getRemaining());

    for (int i = 0; i < NUMBER_OF_CARDS; i++) {
      deck.dealOneCard();
    }
    assertEquals(0, deck.getRemaining());

    assertThrows(NoSuchElementException.class, deck::dealOneCard);
  }
}
