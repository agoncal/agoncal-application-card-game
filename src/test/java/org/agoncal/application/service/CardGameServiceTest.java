package org.agoncal.application.service;

import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
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

@QuarkusTest
class CardGameServiceTest {

  @Inject
  CardGameService service;

  @Test
  public void shouldNotPlayANullGame() {
    assertThrows(ConstraintViolationException.class, () -> service.play(null));
  }

  @Test
  @Disabled("@Valid does not work")
  public void shouldNotPlayADefaultGame() {
    Game game = new Game();
    assertThrows(ConstraintViolationException.class, () -> service.play(game));
  }

  @Test
  public void shouldPlayAGameWithTwoCardsSameSuit() {
    Game game = new Game();
    game.getPlayerOne().takeCard(new Card());
    game.getPlayerTwo().takeCard(new Card());
    assertEquals(0, game.getTable().size());
    game = service.play(game);
    assertEquals(2, game.getTable().size());
    assertEquals(game.getPlayerTwo(), game.getWinner());
  }

  @Test
  public void shouldPlayAGameWithFourCardsSameSuit() {
    Game game = new Game();
    game.getPlayerOne().takeCard(new Card(CLUBS, 1));
    game.getPlayerTwo().takeCard(new Card(DIAMONDS, 2));
    game.getPlayerOne().takeCard(new Card());
    game.getPlayerTwo().takeCard(new Card());
    assertEquals(0, game.getTable().size());
    game = service.play(game);
    assertEquals(4, game.getTable().size());
    assertEquals(game.getPlayerTwo(), game.getWinner());
  }

  @Test
  public void shouldPlayAGameWithFiveCardsSameSuit() {
    Game game = new Game();
    game.getPlayerOne().takeCard(new Card(CLUBS, 1));
    game.getPlayerTwo().takeCard(new Card(DIAMONDS, 2));
    game.getPlayerOne().takeCard(new Card(HEARTS, 3));
    game.getPlayerTwo().takeCard(new Card());
    game.getPlayerOne().takeCard(new Card());
    assertEquals(0, game.getTable().size());
    game = service.play(game);
    assertEquals(5, game.getTable().size());
    assertEquals(game.getPlayerOne(), game.getWinner());
  }

  @Test
  public void shouldPlayAGameWithNoEquivalentSuit() {
    Game game = new Game();
    for (int i = 0; i < 30; i++) {
      game.getPlayerOne().takeCard(new Card(CLUBS, 1));
      game.getPlayerTwo().takeCard(new Card(DIAMONDS, 2));
    }
    assertEquals(0, game.getTable().size());
    game = service.play(game);
    assertEquals(52, game.getTable().size());
    assertNull(game.getWinner());
  }

  @Test
  public void shouldStartGame() {
    Game game = service.startsANewGame();
    assertEquals(0, game.getTable().size());
    assertEquals(26, game.getPlayerOne().getHandSize());
    assertEquals(26, game.getPlayerTwo().getHandSize());
    assertNotNull(game.getPlayerOne());
    assertNotNull(game.getPlayerTwo());
    assertNotNull(game.getCurrentPlayer());
    assertEquals(1, game.getRoundsPlayed());
    assertFalse(game.isGameOver());
  }


  @Test
  public void shouldSwitchCurrentPlayer() {
    Game game = service.startsANewGame();
    Player currentPlayer = game.getCurrentPlayer();
    assertEquals(currentPlayer, game.getCurrentPlayer());

    game = service.switchCurrentPlayer(game);
    assertNotEquals(currentPlayer, game.getCurrentPlayer());

    game = service.switchCurrentPlayer(game);
    assertEquals(currentPlayer, game.getCurrentPlayer());
  }

  @Test
  public void shouldCheckSuitMatchLowerTwo() {
    Game game = service.startsANewGame();
    assertEquals(0, game.getTable().size());
    assertFalse(service.suitsAreEquivalent(game));
  }

  @Test
  public void shouldCheckSuitMatchSameSuit() {
    Game game = service.startsANewGame();
    assertEquals(0, game.getTable().size());
    game.setTable(List.of(new Card(), new Card()));
    assertEquals(2, game.getTable().size());
    assertTrue(service.suitsAreEquivalent(game));
  }

  @Test
  public void shouldCheckSuitMatchDifferentSuit() {
    Game game = service.startsANewGame();
    assertEquals(0, game.getTable().size());
    game.setTable(List.of(new Card(DIAMONDS, 1), new Card(HEARTS, 1)));
    assertEquals(2, game.getTable().size());
    assertFalse(service.suitsAreEquivalent(game));
  }

  @Test
  public void shouldCollectCards() {
    Game game = service.startsANewGame();
    game.setTable(new ArrayList<>(List.of(new Card(DIAMONDS, 1), new Card(CLUBS, 1))));
    assertEquals(2, game.getTable().size());
    game = service.currentPlayerCollectTableCards(game);
    assertEquals(0, game.getTable().size());
  }

  @Test
  @Disabled
  public void shouldPlayRoundOver() {
    Game game = service.startsANewGame();
    Player playerOne = game.getPlayerOne();
    for (int i = 0; i < 52; i++) {
      playerOne.takeCard(new Card());
    }
    game = service.playsSeveralRounds(game);
    assertTrue(game.isGameOver());
  }

}
