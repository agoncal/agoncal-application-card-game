package org.agoncal.application.cli;
/*
 * Main class for running the simple card game.
 */

import org.agoncal.application.service.CardGameService;

public class CardGameMain {

  // Main method

  public static void main(String[] args) {
    new CardGameService().playGame();
  }

}
