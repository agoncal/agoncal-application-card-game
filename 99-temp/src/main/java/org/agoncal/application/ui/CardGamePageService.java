package org.agoncal.application.ui;
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
import org.agoncal.application.service.apis.DeckOfCards;
import org.agoncal.application.service.apis.DeckOfCardsAPI;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CardGamePageService {

  @Inject
  Logger logger;

  // ======================================
  // =              Methods               =
  // ======================================

  @Inject
  @RestClient
  DeckOfCardsAPI proxy;

  public Game newGame(Game game, String namePlayerOne, String namePlayerTwo) {
    game = new Game(namePlayerOne, namePlayerTwo);
    DeckOfCards deckOfCards = proxy.newDeck(1);
    game.getDeck().setId(deckOfCards.getDeckId());
    return game;
  }

  public Game playCard(Game game) {

    DeckOfCards deckOfCards = proxy.dealOneCard(game.getDeck().getId(), 1);

      // Current player places card on table
      Card cardToPlay = new Card(deckOfCards.getCards().get(0).getSuit().toString(), deckOfCards.getCards().get(0).getName());
      game.getTable().add(cardToPlay);
      logger.debug(game.getCurrentPlayer().getName() + " plays a " + cardToPlay);

      // If the two last suits are equivalence, then the current player wins the fight
      if (suitsAreEquivalent(game)) {
        logger.info(game.getCurrentPlayer().getName() + " wins!");
        game.setWinner(game.getCurrentPlayer());
      } else {
        switchCurrentPlayer(game);
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

    // The table needs at least two cards
    if (tableSize < 2) {
      return false;
    }

    // If the suits are equivalence, then the current player wins the fight
    return game.getTable().get(tableSize - 1).getSuit() == game.getTable().get(tableSize - 2).getSuit();
  }
}
