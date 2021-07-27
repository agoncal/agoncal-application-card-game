package org.agoncal.application.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.agoncal.application.model.Suit.HEARTS;
import static org.agoncal.application.model.Suit.SPADES;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class CardTest {

  @Test
  public void shouldCheckCardCode() {
    Card card = new Card(1, CLUBS);
    assertEquals("AC", card.getCode());
    assertEquals("ACE", card.getValue());
    assertEquals("A♣", card.toString());
    assertEquals(CLUBS, card.getSuit());

    card = new Card(2, HEARTS);
    assertEquals("2H", card.getCode());
    assertEquals("2", card.getValue());
    assertEquals("2❤", card.toString());
    assertEquals(HEARTS, card.getSuit());

    card = new Card(11, DIAMONDS);
    assertEquals("JD", card.getCode());
    assertEquals("JACK", card.getValue());
    assertEquals("J♦", card.toString());
    assertEquals(DIAMONDS, card.getSuit());

    card = new Card(12, DIAMONDS);
    assertEquals("QD", card.getCode());
    assertEquals("QUEEN", card.getValue());
    assertEquals("Q♦", card.toString());
    assertEquals(DIAMONDS, card.getSuit());

    card = new Card(13, SPADES);
    assertEquals("KS", card.getCode());
    assertEquals("KING", card.getValue());
    assertEquals("K♠", card.toString());
    assertEquals(SPADES, card.getSuit());
  }
}
