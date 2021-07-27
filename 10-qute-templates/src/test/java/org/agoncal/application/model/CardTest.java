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
  public void shouldCheckCardImage() {
    assertEquals("https://deckofcardsapi.com/static/img/AC.png", new Card(CLUBS, 1).getImage());
    assertEquals("https://deckofcardsapi.com/static/img/2H.png", new Card(HEARTS, 2).getImage());
    assertEquals("https://deckofcardsapi.com/static/img/JD.png", new Card(DIAMONDS, 11).getImage());
    assertEquals("https://deckofcardsapi.com/static/img/QD.png", new Card(DIAMONDS, 12).getImage());
    assertEquals("https://deckofcardsapi.com/static/img/KS.png", new Card(SPADES, 13).getImage());
  }

  @Test
  public void shouldCheckCardCode() {
    assertEquals("ACEC", new Card(CLUBS, 1).getCode());
    assertEquals("2H", new Card(HEARTS, 2).getCode());
    assertEquals("JACKD", new Card(DIAMONDS, 11).getCode());
    assertEquals("QUEEND", new Card(DIAMONDS, 12).getCode());
    assertEquals("KINGS", new Card(SPADES, 13).getCode());

    assertEquals("ACEC", new Card("CLUBS", "ACE").getCode());
    assertEquals("2H", new Card("HEARTS", "2").getCode());
    assertEquals("JACKD", new Card("DIAMONDS", "JACK").getCode());
    assertEquals("QUEEND", new Card("DIAMONDS", "QUEEN").getCode());
    assertEquals("KINGS", new Card("SPADES", "KING").getCode());

    assertEquals(1, new Card("CLUBS", "ACE").getValue());
    assertEquals(2, new Card("HEARTS", "2").getValue());
    assertEquals(11, new Card("DIAMONDS", "JACK").getValue());
    assertEquals(12, new Card("DIAMONDS", "QUEEN").getValue());
    assertEquals(13, new Card("SPADES", "KING").getValue());

    assertEquals(CLUBS, new Card("CLUBS", "ACE").getSuit());
    assertEquals(HEARTS, new Card("HEARTS", "2").getSuit());
    assertEquals(DIAMONDS, new Card("DIAMONDS", "JACK").getSuit());
    assertEquals(DIAMONDS, new Card("DIAMONDS", "QUEEN").getSuit());
    assertEquals(SPADES, new Card("SPADES", "KING").getSuit());
  }
}
