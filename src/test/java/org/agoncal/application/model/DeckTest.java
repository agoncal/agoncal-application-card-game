package org.agoncal.application.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class DeckTest {

  @Test
  public void shouldCreateADeck() {
    Deck deck = new Deck();
    assertEquals(53, deck.getCards().length);
    assertEquals(1, deck.getNext());
  }

  @Test
  public void shouldDealACard() {
    Deck deck = new Deck();
    assertEquals(53, deck.getCards().length);
    assertEquals(1, deck.getNext());
    deck.dealOneCard();
    assertEquals(53, deck.getCards().length);
    assertEquals(2, deck.getNext());
  }

  @Test
  public void shouldDealCards() {
    Deck deck = new Deck();
    assertEquals(1, deck.getNext());
    deck.dealOneCard();
    assertEquals(2, deck.getNext());
    deck.dealOneCard();
    assertEquals(3, deck.getNext());
    deck.dealOneCard();
    assertEquals(4, deck.getNext());
    deck.dealOneCard();
    assertEquals(5, deck.getNext());
    deck.dealOneCard();
    assertEquals(6, deck.getNext());
    deck.dealOneCard();
    assertEquals(7, deck.getNext());
    deck.dealOneCard();
    assertEquals(8, deck.getNext());
    deck.dealOneCard();
    assertEquals(9, deck.getNext());
    deck.dealOneCard();
    assertEquals(10, deck.getNext());
    deck.dealOneCard();
    assertEquals(11, deck.getNext());
    deck.dealOneCard();
    assertEquals(12, deck.getNext());
    deck.dealOneCard();
    assertEquals(13, deck.getNext());
    deck.dealOneCard();
    assertEquals(14, deck.getNext());
    deck.dealOneCard();
    assertEquals(15, deck.getNext());
    deck.dealOneCard();
    assertEquals(16, deck.getNext());
    deck.dealOneCard();
    assertEquals(17, deck.getNext());
    deck.dealOneCard();
    assertEquals(18, deck.getNext());
    deck.dealOneCard();
    assertEquals(19, deck.getNext());
    deck.dealOneCard();
    assertEquals(20, deck.getNext());
    deck.dealOneCard();
    assertEquals(21, deck.getNext());
    deck.dealOneCard();
    assertEquals(22, deck.getNext());
    deck.dealOneCard();
    assertEquals(23, deck.getNext());
    deck.dealOneCard();
    assertEquals(24, deck.getNext());
    deck.dealOneCard();
    assertEquals(25, deck.getNext());
    deck.dealOneCard();
    assertEquals(26, deck.getNext());
    deck.dealOneCard();
    assertEquals(27, deck.getNext());
    deck.dealOneCard();
    assertEquals(28, deck.getNext());
    deck.dealOneCard();
    assertEquals(29, deck.getNext());
    deck.dealOneCard();
    assertEquals(30, deck.getNext());
    deck.dealOneCard();
    assertEquals(31, deck.getNext());
    deck.dealOneCard();
    assertEquals(32, deck.getNext());
    deck.dealOneCard();
    assertEquals(33, deck.getNext());
    deck.dealOneCard();
    assertEquals(34, deck.getNext());
    deck.dealOneCard();
    assertEquals(35, deck.getNext());
    deck.dealOneCard();
    assertEquals(36, deck.getNext());
    deck.dealOneCard();
    assertEquals(37, deck.getNext());
    deck.dealOneCard();
    assertEquals(38, deck.getNext());
    deck.dealOneCard();
    assertEquals(39, deck.getNext());
    deck.dealOneCard();
    assertEquals(40, deck.getNext());
    deck.dealOneCard();
    assertEquals(41, deck.getNext());
    deck.dealOneCard();
    assertEquals(42, deck.getNext());
    deck.dealOneCard();
    assertEquals(43, deck.getNext());
    deck.dealOneCard();
    assertEquals(44, deck.getNext());
    deck.dealOneCard();
    assertEquals(45, deck.getNext());
    deck.dealOneCard();
    assertEquals(46, deck.getNext());
    deck.dealOneCard();
    assertEquals(47, deck.getNext());
    deck.dealOneCard();
    assertEquals(48, deck.getNext());
    deck.dealOneCard();
    assertEquals(49, deck.getNext());
    deck.dealOneCard();
    assertEquals(50, deck.getNext());
    deck.dealOneCard();
    assertEquals(51, deck.getNext());
    deck.dealOneCard();
    assertEquals(52, deck.getNext());
    deck.dealOneCard();
    assertEquals(53, deck.getNext());

    assertThrows(ArrayIndexOutOfBoundsException.class, deck::dealOneCard);
  }
}
