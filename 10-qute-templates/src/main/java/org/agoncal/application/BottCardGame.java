package org.agoncal.application;
/*
 * Main class for running the simple card game.
 *
 * A simple card game with an option for two players.
 * The deck of cards contains 52 cards with 13 cards each in the 4 suits:
 * clubs, diamonds, hearts, spades.
 *
 * Each player begins with 26 cards and one of the players starts the game
 * by putting their first card on the table. Players take turns by putting the
 * top card from their hand, until the current card placed on the table matches
 * the suit of the previous card on the table. If a match happens, the player
 * whose card matched gets all the cards currently on the table and adds them
 * at the end of the cards currently in his or her hand. Game continues until
 * one player gets all 52 cards, or for 10 rounds.
 *
 * Construct the game using the following guidelines:
 *
 * 1. Create a method to deal the deck of cards so that each player gets 26
 * random cards.
 *
 * 2. Start the game by choosing either of the player randomly.
 *
 * 3. Show the cards on the table and in the hand of each player at each step
 * of the game.
 *
 * 4. Continue the game for 10 rounds or until one player has all the cards,
 * whichever happens first.
 *
 * 5. Declare the winner (the player with all the cards, or with more cards
 * after 10 rounds), or say its a tie (when both players have equal number
 * of cards after 10 rounds).
 *
 * Must use at least one singly linked list, one 2D array, methods to separate
 * work (main method should not have more than 20 lines of code).
 *
 * Due in full by 11/16/2015 @ 11:59 PM.
 *
 */

import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.service.DeckService;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@RequestScoped
public class BottCardGame {

  @Inject
  Logger logger;

  @Inject
  DeckService deckService;

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

  public Game playOneCard(@NotNull Game game) {

    // Current player places card on table
    Card card = deckService.dealOneCard(game.getDeck().getId());
    logger.debug("Deck " + game.getDeck().getId() + " remains " + game.getDeck().getRemaining() + " cards");

    game.currentPlayerPlaysOneCard(card);
    logger.debug(game.getCurrentPlayer().getName() + " plays a " + card + " table has now " + game.getTable().size() + "cards");

    // If the suits are equivalence, then the current player wins the fight
    if (game.twoLastSuitsAreEquivalent()) {
      logger.info(game.getCurrentPlayer().getName() + " wins!");
      game.currentPlayerWon();
    } else {
      game.switchCurrentPlayer();
    }

    return game;
  }
}
