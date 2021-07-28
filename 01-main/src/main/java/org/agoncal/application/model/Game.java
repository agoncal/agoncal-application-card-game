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
  private Deck deck = new Deck();
  private List<Card> table = new ArrayList<>();
  private boolean over;

  public static final String NAME_PLAYER_ONE = "Alice";
  public static final String NAME_PLAYER_TWO = "Bob";

  // ======================================
  // =            Constructors            =
  // ======================================

  public Game() {
    this(NAME_PLAYER_ONE, NAME_PLAYER_TWO);
  }

  public Game(String namePlayerOne, String namePlayerTwo) {
    this.playerOne = new Player(namePlayerOne);
    this.playerTwo = new Player(namePlayerTwo);
    this.currentPlayer = playerOne;
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public void currentPlayerPlaysOneCard(Card card) {
    this.currentPlayer.playCard(card);
    this.table.add(card);
    switchCurrentPlayer();
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
    if (currentPlayer == playerOne)
      currentPlayer = playerTwo;
    else if (currentPlayer == playerTwo)
      currentPlayer = playerOne;
  }

  public boolean isOver() {
    if (table.size() == NUMBER_OF_CARDS) {
      over = true;
    }

    return over;
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
