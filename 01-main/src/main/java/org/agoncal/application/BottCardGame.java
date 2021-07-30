package org.agoncal.application;
/*
 * Main class for running the Bott Card game.
 *
 * The Bott Card Game is played by two Botts.
 * The deck of cards contains 52 cards with 13 cards each in the 4 suits:
 * clubs, diamonds, hearts, spades.
 *
 * Each Bott picks up a card from the deck and
 * puts it on the table. Botts take turns by putting the
 * nex deck card, until the current card placed on the table matches
 * the suit of the previous card on the table. If a match happens, the Bott
 * whose card matched wins.
 *
 */

import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.service.DeckService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BottCardGame {

  DeckService deckService = new DeckService();

  // ======================================
  // =              Methods               =
  // ======================================

  public Game startANewGame() {
    return startANewGame(null, null);
  }

  public Game startANewGame(String namePlayerOne, String namePlayerTwo) {
    Game game = new Game();

    // Initializes the card game
    game.initialize(namePlayerOne, namePlayerTwo);

    // Sets a deck of cards
    game.setDeck(deckService.newShuffledDeck());

    return game;
  }

  public Game playOneCard(Game game) {

    // Current player places card on table
    Card card = deckService.dealOneCard(game.getDeck().getId());
    game.currentPlayerPlaysOneCard(card);

    // If the suits are equivalence, then the current player wins the fight
    if (game.twoLastSuitsAreEquivalent()) {
      game.currentPlayerWon();
    } else {
      game.switchCurrentPlayer();
    }

    return game;
  }
}
