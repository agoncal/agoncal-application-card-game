package org.agoncal.application;

import org.agoncal.application.model.Card;
import org.agoncal.application.model.Game;
import org.agoncal.application.model.Player;

public class BottCardGameMain {

  public static void main(String[] args) {
    BottCardGame service = new BottCardGame();
    Game game = service.startANewGame();

    System.out.println("#####################");
    System.out.println("New game has started between " + game.getPlayerOne() + " and " + game.getPlayerTwo());
    System.out.println(game.getCurrentPlayer() + " starts the game \n");

    while (!game.isOver()) {
      service.playOneCard(game);

      // Display each player's hand
      displayHand(game.getPlayerOne());
      displayHand(game.getPlayerTwo());
      displayTable(game);

      // Sleep for a second before beginning a new round
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
      }
    }
    displayTheWinner(game); // Declare a winner
  }

  private static void displayHand(Player player) {
    System.out.printf("(%2s)", player.getHandSize());
    System.out.printf("%14s", player.getName());

    for (Card card : player.getHand()) {
      System.out.printf("%4s", card);
    }
    System.out.println();
  }

  private static void displayTable(Game game) {
    System.out.printf("(%2s)", game.getTable().size());
    System.out.printf("%14s", "Table");

    for (Card card : game.getTable()) {
      System.out.printf("%4s", card);
    }
    System.out.println("\n");
  }

  private static void displayTheWinner(Game game) {
    if (game.getWinner() == null) {
      System.out.println("TIE! WOW IT'S SUPER RARE!");
    } else {
      System.out.print("\n" + game.getWinner().getName().toUpperCase() + " WINS WITH A TOTAL OF " + game.getWinner().getHandSize() + " CARDS! ");
    }
  }
}
