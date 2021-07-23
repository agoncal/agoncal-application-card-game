package org.agoncal.application.cli;
/*
 * Main class for running the simple card game.
 */

import org.agoncal.application.model.Game;
import org.agoncal.application.service.CardGameService;

public class CardGameMain {

  // Main method

  private CardGameService service = new CardGameService();

  public static void main(String[] args) {
    new CardGameMain().playGame();
  }

  // Play the simple card game
  public void playGame() {
    System.out.println("Starting simple card game simulation...");
    System.out.println();

    Game game = new Game();

    game = service.dealCards(game); // Deal 26 cards to each player
    game = service.chooseFirstPlayer(game); // Choose who goes first
    game = service.playRounds(game); // Start the rounds
    game = service.declareWinner(game); // Declare a winner
  }

}
