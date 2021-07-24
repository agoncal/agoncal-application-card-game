package org.agoncal.application.cli;
/*
 * Main class for running the simple card game.
 */

import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.model.Player;
import org.agoncal.application.service.CardGameService;

import static org.agoncal.application.model.Game.MAX_ROUND;

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

    // Starts the game
    Game game = new Game();
    game = service.startGame(game);

    // Play rounds
    while (!game.isGameOver()) {
      // Display the round number
      System.out.println("ROUND " + game.getRoundsPlayed() + " out of " + MAX_ROUND);
      System.out.println();

      // Display each player's hand
      displayHands(game);

      // Play individual round
      game = service.playRound(game);

    }

    // Declares the winner of the game
    game = service.declareWinner(game); // Declare a winner
  }


  private void displayHands(Game game) {
    displayHand(game.getPlayerOne());
    displayHand(game.getPlayerTwo());
  }

  public void displayHand(Player player) {
    System.out.println(player.getName() + "\'s hand (" + player.getHandSize() + "):");

    int grid = 0;
    for (Card card : player.getCards()) {
      System.out.print(String.format("%4s", card));
      grid++;
      if (grid % 4 == 0) {
        System.out.println();
        grid = 0;
      }
    }
    System.out.println();
  }

}
