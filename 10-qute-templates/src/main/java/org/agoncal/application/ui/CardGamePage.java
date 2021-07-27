package org.agoncal.application.ui;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.application.model.Game;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import static org.agoncal.application.model.Game.NAME_PLAYER_ONE;
import static org.agoncal.application.model.Game.NAME_PLAYER_TWO;

@Path("/play")
@ApplicationScoped
public class CardGamePage {

  @Inject
  CardGamePageService service;

  Game game;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance play(Game game);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance playANewGame(@QueryParam("one") @DefaultValue(NAME_PLAYER_ONE) String namePlayerOne, @QueryParam("two") @DefaultValue(NAME_PLAYER_TWO) String namePlayerTwo) {
    game = service.newGame(game, namePlayerOne, namePlayerTwo);
    return Templates.play(game);
  }

  @Path("/next")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance nextCard(@QueryParam("one") @DefaultValue(NAME_PLAYER_ONE) String namePlayerOne, @QueryParam("two") @DefaultValue(NAME_PLAYER_TWO) String namePlayerTwo, @QueryParam("deck_id") String deckId) {
    return Templates.play(service.playCard(game));
  }
}
