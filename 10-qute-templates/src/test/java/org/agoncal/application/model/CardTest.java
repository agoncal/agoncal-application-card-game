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
    assertEquals("ACEC", new Card(1, CLUBS).getCode());
    assertEquals("2H", new Card(2, HEARTS).getCode());
    assertEquals("JACKD", new Card(11, DIAMONDS).getCode());
    assertEquals("QUEEND", new Card(12, DIAMONDS).getCode());
    assertEquals("KINGS", new Card(13, SPADES).getCode());

    assertEquals("ACEC", new Card("ACE", CLUBS).getCode());
    assertEquals("2H", new Card("2", HEARTS).getCode());
    assertEquals("JACKD", new Card("JACK", DIAMONDS).getCode());
    assertEquals("QUEEND", new Card("QUEEN", DIAMONDS).getCode());
    assertEquals("KINGS", new Card("KING", SPADES).getCode());

    assertEquals(1, new Card("ACE", CLUBS).getValue());
    assertEquals(2, new Card("2", HEARTS).getValue());
    assertEquals(11, new Card("JACK", DIAMONDS).getValue());
    assertEquals(12, new Card("QUEEN", DIAMONDS).getValue());
    assertEquals(13, new Card("KING", SPADES).getValue());

    assertEquals(CLUBS, new Card("ACE", CLUBS).getSuit());
    assertEquals(HEARTS, new Card("2", HEARTS).getSuit());
    assertEquals(DIAMONDS, new Card("JACK", DIAMONDS).getSuit());
    assertEquals(DIAMONDS, new Card("QUEEN", DIAMONDS).getSuit());
    assertEquals(SPADES, new Card("KING", SPADES).getSuit());
  }
}
