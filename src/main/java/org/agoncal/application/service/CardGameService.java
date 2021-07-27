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
import org.agoncal.application.model.Deck;
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

import static org.agoncal.application.model.Deck.NUMBER_OF_CARDS;
import static org.agoncal.application.model.Game.NAME_PLAYER_ONE;
import static org.agoncal.application.model.Game.NAME_PLAYER_TWO;

@ApplicationScoped
public class CardGameService {

  @Inject
  Logger logger;

  // ======================================
  // =              Methods               =
  // ======================================

  public Game startsANewGame() {
    Game game = new Game();

    // Deals 26 cards to each player in alternating order
    Deck deck = new Deck();
    for (int i = 0; i < (NUMBER_OF_CARDS / 2); i++) {
      game.getPlayerOne().takeCard(deck.dealOneCard());
      game.getPlayerTwo().takeCard(deck.dealOneCard());
    }

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

  // Play an individual round
  // Play an individual round
  public Game playsSeveralRounds(Game game) {
    boolean suitFight = false; // Flag for notifying a suit fight
    Card cardToPlay;

    // If one player reaches 52 cards, then it's the end of the game, the player won
    if ((game.getPlayerOne().getHandSize() == NUMBER_OF_CARDS) || (game.getPlayerTwo().getHandSize() == NUMBER_OF_CARDS)) {
      game.setGameOver(true);
      return game;
    }

    System.out.println("--- Cards on the table");
    while (!suitFight) {
      // Current player places card on table
      cardToPlay = game.getCurrentPlayer().playCard();
      System.out.println(String.format("%5s", game.getCurrentPlayer().getName()) + " plays a " + cardToPlay + "!");
      game.getTable().add(cardToPlay);

      // Check if there's a suit match
      suitFight = suitsAreEquivalent(game);

      if (!suitFight)
        switchCurrentPlayer(game);
    }

    // Print a message
    System.out.print(game.getCurrentPlayer().getName() + " takes the table (" + game.getTable().size() + "): ");
    displayTable(game);

    game = currentPlayerCollectTableCards(game);
    System.out.println();

    // Sleep for a second before beginning a new round
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
    }

    // Increment rounds played counter
    game.incrementRound();
    return game;
  }

  @Inject
  @RestClient
  DeckOfCardsAPI proxy;

  public Game newGame() {
    return newGame(NAME_PLAYER_ONE, NAME_PLAYER_TWO);
  }

  public Game newGame(String namePlayerOne, String namePlayerTwo) {
    Game game = new Game(namePlayerOne, namePlayerTwo);
    DeckOfCards deckOfCards = proxy.newDeck(1);
    game.getDeck().setId(deckOfCards.getDeckId());
    return game;
  }

  public Game play() {
    return play(NAME_PLAYER_ONE, NAME_PLAYER_TWO);
  }

  public Game play(String namePlayerOne, String namePlayerTwo) {
    Game game = new Game(namePlayerOne, namePlayerTwo);

    // Deals 26 cards to each player in alternating order
    Deck deck = new Deck();
    for (int i = 0; i < (NUMBER_OF_CARDS / 2); i++) {
      Card cardPlayerOne = deck.dealOneCard();
      Card cardPlayerTwo = deck.dealOneCard();
      game.getPlayerOne().takeCard(cardPlayerOne);
      game.getPlayerTwo().takeCard(cardPlayerTwo);
      logger.debug("Card player one (" + cardPlayerOne + ") - Card player two (" + cardPlayerTwo + ") - Remains " + deck.getRemaining() + " cards of deck " + deck.getId());
    }
    return play(game);
  }

  public Game play(@NotNull @Valid Game game) {
    boolean twoLastSuitsAreEquivalent = false;

    while (!twoLastSuitsAreEquivalent && game.getTable().size() != NUMBER_OF_CARDS) {

      // Current player places card on table
      Card cardToPlay = game.getCurrentPlayer().playCard();
      game.getTable().add(cardToPlay);
      logger.debug(game.getCurrentPlayer().getName() + " plays a " + cardToPlay);

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

  // Collect cards from table
  Game currentPlayerCollectTableCards(Game game) {
    // Current player takes each card from the table and adds to hand
    for (int i = 0; i < game.getTable().size(); i++) {
      Card cardToTake = game.getTable().get(i);
      game.getCurrentPlayer().takeCard(cardToTake);
    }

    game.getTable().clear();
    return game;
  }

  // Displays all the cards currently on the table
  void displayTable(Game game) {
    for (int i = 0; i < game.getTable().size(); i++) {
      if (game.getTable().get(i) != null) {
        System.out.print(game.getTable().get(i).getCode() + " ");
      }
    }

    System.out.println();
  }
}
