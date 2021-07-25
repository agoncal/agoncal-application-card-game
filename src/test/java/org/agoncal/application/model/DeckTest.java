package org.agoncal.application.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.agoncal.application.model.Game.NUMBER_OF_CARDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class DeckTest {

  @Test
  public void shouldCreateADeck() {
    Deck deck = new Deck();
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(0, deck.getNext());
  }

  @Test
  public void shouldDealACard() {
    Deck deck = new Deck();
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(0, deck.getNext());
    deck.dealOneCard();
    assertEquals(NUMBER_OF_CARDS, deck.getCards().size());
    assertEquals(1, deck.getNext());
  }

  @Test
  public void shouldDeal53Cards() {
    Deck deck = new Deck();
    assertEquals(0, deck.getNext());

    for (int i = 0; i < NUMBER_OF_CARDS; i++) {
      deck.dealOneCard();
      assertEquals(i + 1, deck.getNext());
    }

    assertThrows(IndexOutOfBoundsException.class, deck::dealOneCard);
  }
}
