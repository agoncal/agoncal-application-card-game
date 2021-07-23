package org.agoncal.application.service;

import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.model.Player;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class CardGameServiceTest {

  @Inject
  CardGameService service;

  @Test
  public void shouldDealCards() {
    Game game = new Game();
    assertEquals(0, game.getTable().size());
    assertEquals(53, game.getDeck().getCards().length);
    assertEquals(0, game.getPlayerOne().getHandSize());
    assertEquals(0, game.getPlayerTwo().getHandSize());
    game = service.dealCards(game);
    assertEquals(0, game.getTable().size());
    assertEquals(53, game.getDeck().getCards().length);
    assertEquals(26, game.getPlayerOne().getHandSize());
    assertEquals(26, game.getPlayerTwo().getHandSize());
  }


  @Test
  public void shouldSwitchCurrentPlayer() {
    Player playerOne = new Player("Bob");
    Player playerTwo = new Player("Alice");
    Game game = new Game(playerOne, playerTwo);
    assertEquals("Bob", game.getCurrentPlayer().getName());
    game = service.switchCurrentPlayer(game);
    assertEquals("Alice", game.getCurrentPlayer().getName());
  }

  @Test
  public void shouldSwitchDefaultPlayer() {
    Game game = new Game();
    assertEquals("Bob", game.getCurrentPlayer().getName());
    game = service.switchCurrentPlayer(game);
    assertEquals("Alice", game.getCurrentPlayer().getName());
  }

  @Test
  public void shouldCheckSuitMatchLowerTwo() {
    Game game = new Game();
    assertEquals(0, game.getTable().size());
    assertFalse(service.checkSuitMatch(game));
  }

  @Test
  public void shouldCheckSuitMatchSameSuit() {
    Game game = new Game();
    assertEquals(0, game.getTable().size());
    game.setTable(List.of(new Card(), new Card()));
    assertEquals(2, game.getTable().size());
    assertTrue(service.checkSuitMatch(game));
  }

  @Test
  public void shouldCheckSuitMatchDifferentSuit() {
    Game game = new Game();
    assertEquals(0, game.getTable().size());
    game.setTable(List.of(new Card(1, 1), new Card(2, 1)));
    assertEquals(2, game.getTable().size());
    assertFalse(service.checkSuitMatch(game));
  }


  @Test
  public void shouldCollectCards() {
    Game game = new Game();
    game.setTable(new ArrayList<>(List.of(new Card(1, 1), new Card(2, 1))));
    assertEquals(2, game.getTable().size());
    game = service.collectCards(game);
    assertEquals(0, game.getTable().size());
  }

}
