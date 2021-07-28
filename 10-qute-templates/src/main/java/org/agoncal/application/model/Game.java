package org.agoncal.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.agoncal.application.model.Deck.NUMBER_OF_CARDS;
import static org.agoncal.application.model.Player.RANDOM_PLAYER_NAME_ONE;
import static org.agoncal.application.model.Player.RANDOM_PLAYER_NAME_TWO;

public class Game {

  // ======================================
  // =             Attributes             =
  // ======================================

  private Player playerOne;
  private Player playerTwo;
  private Player currentPlayer;
  private Player winner;
  private Deck deck = new Deck();
  private List<Card> table = new ArrayList<>();
  private boolean over;

  // ======================================
  // =            Constructors            =
  // ======================================

  // ======================================
  // =              Methods               =
  // ======================================

  public void startANewGame() {
    startANewGame(RANDOM_PLAYER_NAME_ONE, RANDOM_PLAYER_NAME_TWO);
  }

  public void startANewGame(String namePlayerOne, String namePlayerTwo) {

    // Set the player names
    this.playerOne = namePlayerOne == null ? new Player(RANDOM_PLAYER_NAME_ONE) : new Player(namePlayerOne);
    this.playerTwo = namePlayerTwo == null ? new Player(RANDOM_PLAYER_NAME_TWO) : new Player(namePlayerTwo);
    currentPlayer = playerOne;

    // Choose which players goes first
    Random random = new Random();
    if (random.nextBoolean()) {
      switchCurrentPlayer();
    }
  }

  public void currentPlayerPlaysOneCard(Card card) {
    if (currentPlayer == playerOne) {
      this.playerOne.playCard(card);
    } else {
      this.playerTwo.playCard(card);
    }
    this.table.add(card);
  }

  public boolean twoLastSuitsAreEquivalent() {
    int tableSize = table.size();

    // You need at least two cards to fight
    if (tableSize < 2) {
      return false;
    }

    // If the suits are equivalence, then the current player wins the fight and the game is over
    if (table.get(tableSize - 1).getSuit() == table.get(tableSize - 2).getSuit()) {
      this.winner = currentPlayer;
      this.over = true;
      return true;
    } else {
      return false;
    }
  }

  public void switchCurrentPlayer() {
    if (currentPlayer == playerOne) {
      currentPlayer = playerTwo;
    } else {
      currentPlayer = playerOne;
    }
  }

  public boolean isOver() {
    if (table.size() == NUMBER_OF_CARDS) {
      over = true;
    }

    return over;
  }

  // ======================================
  // =        Getters and Setters         =
  // ======================================

  public Player getPlayerOne() {
    return playerOne;
  }

  public void setPlayerOne(Player playerOne) {
    this.playerOne = playerOne;
  }

  public Player getPlayerTwo() {
    return playerTwo;
  }

  public void setPlayerTwo(Player playerTwo) {
    this.playerTwo = playerTwo;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public Player getWinner() {
    return winner;
  }

  public void currentPlayerWon() {
    this.winner = currentPlayer;
    this.over = true;
  }

  public Deck getDeck() {
    return deck;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }

  public List<Card> getTable() {
    return table;
  }

  public void setTable(List<Card> table) {
    this.table = table;
  }
}
