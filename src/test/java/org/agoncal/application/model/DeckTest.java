package org.agoncal.application.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

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
    assertEquals(0, deck.getNext());
  }

  @Test
  public void shouldDealACard() {
    Deck deck = new Deck();
    assertNotNull(deck.getId());
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(NUMBER_OF_CARDS, deck.getRemaining());
    assertEquals(0, deck.getNext());
    deck.dealOneCard();
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(NUMBER_OF_CARDS - 1, deck.getRemaining());
    assertEquals(1, deck.getNext());
  }

  @Test
  public void shouldDeal53Cards() {
    Deck deck = new Deck();
    assertNotNull(deck.getId());
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(NUMBER_OF_CARDS, deck.getRemaining());
    assertEquals(0, deck.getNext());

    for (int i = 0; i < NUMBER_OF_CARDS; i++) {
      deck.dealOneCard();
      assertEquals(i + 1, deck.getNext());
    }
    assertEquals(0, deck.getRemaining());

    assertThrows(IndexOutOfBoundsException.class, deck::dealOneCard);
  }
}
