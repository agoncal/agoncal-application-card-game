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
  public void shouldCheckCardURL() {
    assertEquals("https://deckofcardsapi.com/static/img/AC.png", new Card(CLUBS, 1).getURL());
    assertEquals("https://deckofcardsapi.com/static/img/2H.png", new Card(HEARTS, 2).getURL());
    assertEquals("https://deckofcardsapi.com/static/img/JD.png", new Card(DIAMONDS, 11).getURL());
    assertEquals("https://deckofcardsapi.com/static/img/QD.png", new Card(DIAMONDS, 12).getURL());
    assertEquals("https://deckofcardsapi.com/static/img/KS.png", new Card(SPADES, 13).getURL());
  }
}
