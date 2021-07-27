package org.agoncal.application.service;
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
import org.agoncal.application.model.Player;
import org.agoncal.application.service.apis.DeckOfCards;
import org.agoncal.application.service.apis.DeckOfCardsAPI;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Random;

import static org.agoncal.application.model.Game.NAME_PLAYER_ONE;
import static org.agoncal.application.model.Game.NAME_PLAYER_TWO;

@ApplicationScoped
public class CardGameService {

  @Inject
  Logger logger;

  @Inject
  @RestClient
  DeckOfCardsAPI proxy;

  // ======================================
  // =              Methods               =
  // ======================================

  public Game startsANewGame() {
    return startsANewGame(NAME_PLAYER_ONE, NAME_PLAYER_TWO);
  }

  public Game startsANewGame(String namePlayerOne, String namePlayerTwo) {
    Game game = new Game();
    DeckOfCards deckOfCards = proxy.newDeck(1);
    game.getDeck().setId(deckOfCards.getDeckId());

    // Choose which players goes first
    Random random = new Random();
    int n = random.nextInt(2);

    if (n == 1) {
      // Make playerTwo the new playerOne
      Player temp = game.getPlayerOne();
      game.setPlayerOne(game.getPlayerTwo());
      game.setPlayerTwo(temp);
    }

    return game;
  }

  public Game play(@NotNull @Valid Game game) {
    boolean twoLastSuitsAreEquivalent = false;

    while (!twoLastSuitsAreEquivalent && !game.isGameOver()) {

      // Current player places card on table
      Card cardToPlay = proxy.dealOneCard(game.getDeck().getId(), 1).getCards().get(0);
      game.playOneCard(cardToPlay);
      logger.debug(game.getCurrentPlayer().getName() + " plays a " + cardToPlay + "table has now " + game.getTable().size() + "cards");

      // Checks if the suits are equivalent
      twoLastSuitsAreEquivalent = suitsAreEquivalent(game);

      // If the suits are equivalence, then the current player wins the fight
      if (twoLastSuitsAreEquivalent) {
        logger.info(game.getCurrentPlayer().getName() + " wins!");
        game.setWinner(game.getCurrentPlayer());
      } else {
        switchCurrentPlayer(game);
      }
    }

    return game;
  }

  // ======================================
  // =          Private Methods           =
  // ======================================

  // Switch current player
  Game switchCurrentPlayer(Game game) {
    if (game.getCurrentPlayer() == game.getPlayerOne())
      game.setCurrentPlayer(game.getPlayerTwo());
    else if (game.getCurrentPlayer() == game.getPlayerTwo())
      game.setCurrentPlayer(game.getPlayerOne());

    return game;
  }

  // Checks if the two last suits are equivalent
  boolean suitsAreEquivalent(Game game) {
    int tableSize = game.getTable().size();

    // You need at least two cards to fight
    if (tableSize < 2) {
      return false;
    }

    // If the suits are equivalence, then the current player wins the fight
    return game.getTable().get(tableSize - 1).getSuit() == game.getTable().get(tableSize - 2).getSuit();
  }
}
