package org.agoncal.application.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class GameTest {

  @Test
  public void shouldPlayGame() {
    // Init
    Game game = new Game();
    assertEquals(0, game.getTable().size());
    assertEquals(0, game.getPlayerOne().getHandSize());
    assertEquals(0, game.getPlayerTwo().getHandSize());
    assertEquals(game.getPlayerOne(), game.getCurrentPlayer());
    assertFalse(game.twoLastSuitsAreEquivalent());
    assertFalse(game.isOver());

    game.switchCurrentPlayer();
    assertEquals(game.getPlayerTwo(), game.getCurrentPlayer());

    // Play one card
    game.currentPlayerPlaysOneCard(new Card());
    assertEquals(1, game.getTable().size());
    assertEquals(0, game.getPlayerOne().getHandSize());
    assertEquals(1, game.getPlayerTwo().getHandSize());
    assertFalse(game.twoLastSuitsAreEquivalent());
    assertFalse(game.isOver());
    assertEquals(game.getPlayerOne(), game.getCurrentPlayer());
    assertNull(game.getWinner());

    // Play a second card with same suit
    game.currentPlayerPlaysOneCard(new Card());
    assertEquals(2, game.getTable().size());
    assertEquals(1, game.getPlayerOne().getHandSize());
    assertEquals(1, game.getPlayerTwo().getHandSize());
    assertTrue(game.twoLastSuitsAreEquivalent());
    assertTrue(game.isOver());
    assertEquals(game.getPlayerTwo(), game.getCurrentPlayer());
    assertEquals(game.getPlayerTwo(), game.getWinner());
    assertEquals(game.getCurrentPlayer(), game.getWinner());
  }

  @Test
  public void shouldPlayAGameWithNoEquivalentSuit() {
    Game game = new Game();
    assertEquals(0, game.getTable().size());
    assertEquals(0, game.getPlayerOne().getHandSize());
    assertEquals(0, game.getPlayerTwo().getHandSize());

    for (int i = 0; i < 26; i++) {
      game.currentPlayerPlaysOneCard(new Card(1, CLUBS));
      game.currentPlayerPlaysOneCard(new Card(2, DIAMONDS));
      assertFalse(game.twoLastSuitsAreEquivalent());
    }
    assertEquals(26, game.getPlayerOne().getHandSize());
    assertEquals(26, game.getPlayerTwo().getHandSize());
    assertEquals(52, game.getTable().size());
    assertTrue(game.isOver());
    assertNull(game.getWinner());
  }
}
