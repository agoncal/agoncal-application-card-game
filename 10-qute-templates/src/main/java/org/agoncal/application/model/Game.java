package org.agoncal.application.model;

import java.util.ArrayList;
import java.util.List;

import static org.agoncal.application.model.Deck.NUMBER_OF_CARDS;

public class Game {

  // ======================================
  // =             Attributes             =
  // ======================================

  private Player playerOne;
  private Player playerTwo;
  private Player currentPlayer;
  private Player winner;
  private Deck deck;
  private List<Card> table = new ArrayList<>();

  public static final String NAME_PLAYER_ONE = "Alice";
  public static final String NAME_PLAYER_TWO = "Bob";

  // ======================================
  // =            Constructors            =
  // ======================================

  public Game() {
    this(NAME_PLAYER_ONE, NAME_PLAYER_TWO);
  }

  public Game(String namePlayerOne, String namePlayerTwo) {
    this.deck = new Deck();
    this.playerOne = new Player(namePlayerOne);
    this.playerTwo = new Player(namePlayerTwo);
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

  public void playOneCard(Card card) {
    this.currentPlayer.playCard(card);
    this.table.add(card);
  }

  public void switchPlayers() {
    if (currentPlayer == playerOne)
      currentPlayer = playerTwo;
    else if (currentPlayer == playerTwo)
      currentPlayer = playerOne;
  }

  public boolean isGameOver() {
    if ((playerOne.getHandSize() == NUMBER_OF_CARDS) || (playerTwo.getHandSize() == NUMBER_OF_CARDS)) {
      return true;
    }

    if (table.size() == NUMBER_OF_CARDS) {
      return true;
    }

    return false;
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

  public Deck getDeck() {
    return deck;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }
}
