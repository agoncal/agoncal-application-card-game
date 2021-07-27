package org.agoncal.application.service;

import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.agoncal.application.model.Suit.HEARTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest @Disabled
class CardGameServiceTest {

  @Inject
  CardGameService service;

  @Test
  public void shouldNotPlayANullGame() {
    assertThrows(ConstraintViolationException.class, () -> service.playOneCard(null));
  }

  @Test
  @Disabled("@Valid does not work")
  public void shouldNotPlayADefaultGame() {
    Game game = new Game();
    assertThrows(ConstraintViolationException.class, () -> service.playOneCard(game));
  }

  @Test
  public void shouldPlayAGameWithTwoCardsSameSuit() {
    Game game = new Game();
    game.getPlayerOne().playCard(new Card());
    game.getPlayerTwo().playCard(new Card());
    assertEquals(0, game.getTable().size());
    assertEquals(1, game.getPlayerOne().getHand().size());
    assertEquals(1, game.getPlayerTwo().getHand().size());
    game = service.playOneCard(game);
    assertEquals(2, game.getTable().size());
    assertEquals(0, game.getPlayerOne().getHand().size());
    assertEquals(0, game.getPlayerTwo().getHand().size());
    assertEquals(game.getPlayerTwo(), game.getWinner());
  }

  @Test
  public void shouldPlayAGameWithFourCardsSameSuit() {
    Game game = new Game();
    game.getPlayerOne().playCard(new Card(1, CLUBS));
    game.getPlayerTwo().playCard(new Card(2, DIAMONDS));
    game.getPlayerOne().playCard(new Card());
    game.getPlayerTwo().playCard(new Card());
    assertEquals(0, game.getTable().size());
    assertEquals(2, game.getPlayerOne().getHand().size());
    assertEquals(2, game.getPlayerTwo().getHand().size());
    game = service.playOneCard(game);
    assertEquals(4, game.getTable().size());
    assertEquals(0, game.getPlayerOne().getHand().size());
    assertEquals(0, game.getPlayerTwo().getHand().size());
    assertEquals(game.getPlayerTwo(), game.getWinner());
  }

  @Test
  public void shouldPlayAGameWithFiveCardsSameSuit() {
    Game game = new Game();
    game.getPlayerOne().playCard(new Card(1, CLUBS));
    game.getPlayerTwo().playCard(new Card(2, DIAMONDS));
    game.getPlayerOne().playCard(new Card(3, HEARTS));
    game.getPlayerTwo().playCard(new Card());
    game.getPlayerOne().playCard(new Card());
    assertEquals(0, game.getTable().size());
    assertEquals(3, game.getPlayerOne().getHand().size());
    assertEquals(2, game.getPlayerTwo().getHand().size());
    game = service.playOneCard(game);
    assertEquals(5, game.getTable().size());
    assertEquals(0, game.getPlayerOne().getHand().size());
    assertEquals(0, game.getPlayerTwo().getHand().size());
    assertEquals(game.getPlayerOne(), game.getWinner());
  }

  @Test
  public void shouldPlayAGameWithNoEquivalentSuit() {
    Game game = new Game();
    for (int i = 0; i < 30; i++) {
      game.getPlayerOne().playCard(new Card(1, CLUBS));
      game.getPlayerTwo().playCard(new Card(2, DIAMONDS));
    }
    assertEquals(0, game.getTable().size());
    game = service.playOneCard(game);
    assertEquals(52, game.getTable().size());
    assertNull(game.getWinner());
  }

  @Test
  public void shouldStartGame() {
    Game game = service.startANewGame();
    assertEquals(0, game.getTable().size());
    assertEquals(26, game.getPlayerOne().getHandSize());
    assertEquals(26, game.getPlayerTwo().getHandSize());
    assertNotNull(game.getPlayerOne());
    assertNotNull(game.getPlayerTwo());
    assertNotNull(game.getCurrentPlayer());
    assertFalse(game.isOver());
  }


  @Test
  public void shouldSwitchCurrentPlayer() {
    Game game = service.startANewGame();
    Player currentPlayer = game.getCurrentPlayer();
    assertEquals(currentPlayer, game.getCurrentPlayer());

    game.switchCurrentPlayer();
    assertNotEquals(currentPlayer, game.getCurrentPlayer());

    game.switchCurrentPlayer();
    assertEquals(currentPlayer, game.getCurrentPlayer());
  }

  @Test
  public void shouldCheckSuitMatchLowerTwo() {
    Game game = service.startANewGame();
    assertEquals(0, game.getTable().size());

    game = service.playOneCard(game);
    assertEquals(1, game.getTable().size());
    assertFalse(game.twoLastSuitsAreEquivalent());
  }

  @Test
  public void shouldCheckSuitMatchSameSuit() {
    Game game = service.startANewGame();
    assertEquals(0, game.getTable().size());
    game.setTable(List.of(new Card(), new Card()));
    assertEquals(2, game.getTable().size());
    assertTrue(game.twoLastSuitsAreEquivalent());
  }

  @Test
  public void shouldCheckSuitMatchDifferentSuit() {
    Game game = service.startANewGame();
    assertEquals(0, game.getTable().size());
    game.setTable(List.of(new Card(1, DIAMONDS), new Card(1, HEARTS)));
    assertEquals(2, game.getTable().size());
    assertFalse(game.twoLastSuitsAreEquivalent());
  }
}
