package org.agoncal.application.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

  // Attributes

  private Player playerOne;
  private Player playerTwo;
  private Player currentPlayer;
  private Deck deck = new Deck(true); // Shuffle deck automatically
  private List<Card> table = new ArrayList<>();
  private int roundsPlayed = 1;
  private boolean gameOver = false;

  public Game() {
    this.playerOne = new Player("Bob");
    this.playerTwo = new Player("Alice");
    this.currentPlayer = playerOne;
  }

  public Game(Player playerOne, Player playerTwo) {
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.currentPlayer = playerOne;
  }

  // Methods


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

  public int getRoundsPlayed() {
    return roundsPlayed;
  }

  public void setRoundsPlayed(int roundsPlayed) {
    this.roundsPlayed = roundsPlayed;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }
}
