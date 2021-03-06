package org.agoncal.application.cli;
/*
 * Main class for running the simple card game.
 */

import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.model.Player;
import org.agoncal.application.service.CardGameService;

import static org.agoncal.application.model.Game.MAX_ROUND;

public class PlaySeveralRoundsMain {

  // Main method

  private CardGameService service = new CardGameService();

  public static void main(String[] args) {
    new PlaySeveralRoundsMain().playGame();
  }

  // Play the simple card game
  public void playGame() {

    // Starts a new game
    Game game = service.startsANewGame();
    System.out.println("#####################");
    System.out.println("New game has started between " + game.getPlayerOne() + " and " + game.getPlayerTwo());
    System.out.println(game.getCurrentPlayer() + " starts the game");
    System.out.println();

    // Play rounds until the game is over
    while (!game.isGameOver()) {
      // Display the round number
      System.out.println("==================");
      System.out.println("ROUND " + game.getRoundsPlayed() + " out of " + MAX_ROUND);
      System.out.println();

      // Display each player's hand
      displayHands(game);

      // Play individual round
      game = service.playsSeveralRounds(game);

    }

    // Declares the winner of the game
    declaresTheWinner(game); // Declare a winner

    System.out.println("End of the game");
    System.out.println("#####################");
  }

  // Declare a winner
  private void declaresTheWinner(Game game) {
    game.calculateTheWinner();
    if (game.getWinner() == null) {
      System.out.println("TIE! WOW IT'S SUPER RARE!");
    } else {
      System.out.println(game.getWinner().getName().toUpperCase() + " WINS WITH A TOTAL OF " + game.getWinner().getHandSize() + " CARDS!");
    }
    System.out.println();
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
