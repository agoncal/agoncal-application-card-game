package org.agoncal.application.cli;
/*
 * Main class for running the simple card game.
 */

import io.quarkus.runtime.Quarkus;
import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.model.Player;
import org.agoncal.application.service.CardGameService;

import javax.inject.Inject;

//@QuarkusMain
public class PlayOneRoundMain /*implements QuarkusApplication*/ {

  public static void main(String... args) {
    //Quarkus.run(PlayOneRoundMain.class, args);
  }

  @Inject
  CardGameService service;

  public int run(String... args) throws Exception {

    // Play individual round
    Game game = service.play();

    // Display each player's hand
    displayHand(game.getPlayerOne());
    displayHand(game.getPlayerTwo());

    // Declares the winner of the game
    displayTheWinner(game); // Declare a winner
    displayTable(game);

    Quarkus.asyncExit(0);
    return 0;
  }

  private void displayHand(Player player) {
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

  private void displayTheWinner(Game game) {
    if (game.getWinner() == null) {
      System.out.println("TIE! WOW IT'S SUPER RARE!");
    } else {
      System.out.print("\n" + game.getWinner().getName().toUpperCase() + " WINS WITH A TOTAL OF " + game.getWinner().getHandSize() + " CARDS! ");
    }
  }

  void displayTable(Game game) {
    for (int i = 0; i < game.getTable().size(); i++) {
      if (game.getTable().get(i) != null) {
        System.out.print(game.getTable().get(i) + " ");
      }
    }

    System.out.println();
  }
}
