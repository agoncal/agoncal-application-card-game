package org.agoncal.application.service;/*
 * Matt Levan
 * CSC 331, Dr. Amlan Chatterjee
 * Data Structures
 *
 * Project 3 -- Simple Card Game
 *
 * SimulateGame.java
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

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class CardGameService {

  // Deal 26 cards to each hand in alternating order
  public Game dealCards(Game game) {
    for (int i = 0; i < 26; i++) {
      game.getPlayerOne().takeCard(game.getDeck().deal());
      game.getPlayerTwo().takeCard(game.getDeck().deal());
    }
    return game;
  }

  // Choose who goes first
  public Game chooseFirstPlayer(Game game) {
    Random random = new Random();
    int n = random.nextInt(2);

    if (n == 1) { // Make playerTwo the new playerOne
      Player temp = game.getPlayerOne();
      game.setPlayerOne(game.getPlayerTwo());
      game.setPlayerTwo(temp);
    }
    return game;
  }

  // Play rounds, max 10
  public Game playRounds(Game game) {
    while (game.getRoundsPlayed() <= 10 && (game.isGameOver() == false)) {
      // Display the round number
      System.out.println("ROUND " + game.getRoundsPlayed());
      System.out.println();

      // Display each player's hand
      displayHands(game);

      // Play individual round
      playRound(game);

      // Increment roundsPlayed counter
      game.setRoundsPlayed(game.getRoundsPlayed() + 1);
    }
    return game;
  }

  // Play an individual round
  public void playRound(Game game) {
    boolean suitMatch = false; // Flag for notifying a suit match
    Card cardToPlay;

    if ((game.getPlayerOne().getHandSize() == 52) || (game.getPlayerTwo().getHandSize() == 52)) {
      game.setGameOver(true);
    }

    while (suitMatch == false) {
      // Current player places card on table
      cardToPlay = game.getCurrentPlayer().playCard();
      game.getTable().add(cardToPlay);

      // Check if there's a suit match
      suitMatch = checkSuitMatch(game);

      if (suitMatch == false)
        switchCurrentPlayer(game);
    }

    game = collectCards(game);
    System.out.println();

    // Sleep for a second before beginning a new round
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
    }
  }

  // Switch current player
  public Game switchCurrentPlayer(Game game) {
    if (game.getCurrentPlayer() == game.getPlayerOne())
      game.setCurrentPlayer(game.getPlayerTwo());
    else if (game.getCurrentPlayer() == game.getPlayerTwo())
      game.setCurrentPlayer(game.getPlayerOne());

    return game;
  }

  // Check for a suit match
  public boolean checkSuitMatch(Game game) {
    int tableSize = game.getTable().size();
    int lastSuit;
    int topSuit;

    if (tableSize < 2) {
      return false;
    } else {
      lastSuit = game.getTable().get(tableSize - 1).getSuit();
      topSuit = game.getTable().get(tableSize - 2).getSuit();
    }

    // Check suit equivalence
    if (lastSuit == topSuit) {
      System.out.println();
      System.out.println(game.getCurrentPlayer().getName() + " wins the round!");
      System.out.println();

      return true;
    }

    return false;
  }

  // Collect cards from table
  public Game collectCards(Game game) {
    // Print a message
    System.out.print(game.getCurrentPlayer().getName() + " takes the table (" + game.getTable().size() + "): ");
    displayTable(game);

    // Player takes each card from the table and adds to hand
    for (int i = 0; i < game.getTable().size(); i++) {
      Card cardToTake = game.getTable().get(i);
      game.getCurrentPlayer().takeCard(cardToTake);
    }

    game.getTable().clear();

    return game;
  }

  // Displays all the cards currently on the table
  public void displayTable(Game game) {
    for (int i = 0; i < game.getTable().size(); i++) {
      if (game.getTable().get(i) != null) {
        System.out.print(game.getTable().get(i).getName() + " ");
      }
    }

    System.out.println();
    System.out.println();
  }

  // Displays each player's current hand
  public void displayHands(Game game) {
    game.getPlayerOne().displayHand();
    game.getPlayerTwo().displayHand();
  }

  // Declare a winner
  public Game declareWinner(Game game) {
    if (game.getPlayerOne().getHandSize() > game.getPlayerTwo().getHandSize()) {
      System.out.println(game.getPlayerOne().getName().toUpperCase() + " WINS " +
        "WITH A TOTAL OF " + game.getPlayerOne().getHandSize() + " CARDS!");
    } else if (game.getPlayerTwo().getHandSize() > game.getPlayerOne().getHandSize()) {
      System.out.println(game.getPlayerTwo().getName().toUpperCase() + " WINS " +
        "WITH A TOTAL OF " + game.getPlayerTwo().getHandSize() + " CARDS!");
    } else {
      System.out.println("TIE! WOW IT'S SUPER RARE!");
    }

    System.out.println();

    return game;
  }
}
