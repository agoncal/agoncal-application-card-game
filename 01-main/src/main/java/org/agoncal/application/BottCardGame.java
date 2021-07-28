package org.agoncal.application;

import org.agoncal.application.model.Game;
import org.agoncal.application.service.CardGameService;

public class BottCardGame {

  public static void main(String[] args) {
    CardGameService service = new CardGameService();
    Game game = service.startANewGame();

    while (!game.isOver()) {
      service.playOneCard(game);
    }
  }
}
