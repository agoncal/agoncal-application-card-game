package org.agoncal.application.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

  // ======================================
  // =             Attributes             =
  // ======================================

  private Player playerOne;
  private Player playerTwo;
  private Player currentPlayer;
  private Player winner;
  private List<Card> table = new ArrayList<>();
  private int roundsPlayed = 1;
  private boolean gameOver = false;

  public static final int MAX_ROUND = 10;
  public static final int NUMBER_OF_CARDS = 52;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Game() {
    this.playerOne = new Player();
    this.playerTwo = new Player("Alice");
    this.currentPlayer = playerOne;
  }

  public Game(Player playerOne, Player playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.currentPlayer = playerOne;
  }

  // ======================================
  // =              Methods               =
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

  public List<Card> getTable() {
    return table;
  }

  public void setTable(List<Card> table) {
    this.table = table;
  }

  public int getRoundsPlayed() {
    return roundsPlayed;
  }

  public void incrementRound() {
    this.roundsPlayed++;
  }

  public boolean isGameOver() {
    if (roundsPlayed > MAX_ROUND)
      gameOver = true;
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public void calculateTheWinner() {
    if (playerOne.getHandSize() > playerTwo.getHandSize()) {
      winner = playerOne;
    } else if (playerTwo.getHandSize() > playerOne.getHandSize()) {
      winner = playerTwo;
    } else {
      winner = null;
    }
  }

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }
}
