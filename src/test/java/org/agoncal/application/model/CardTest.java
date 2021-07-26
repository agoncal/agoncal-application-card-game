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
    assertEquals("AC", new Card(CLUBS, 1).getCode());
    assertEquals("2H", new Card(HEARTS, 2).getCode());
    assertEquals("JD", new Card(DIAMONDS, 11).getCode());
    assertEquals("QD", new Card(DIAMONDS, 12).getCode());
    assertEquals("KS", new Card(SPADES, 13).getCode());
  }
}
