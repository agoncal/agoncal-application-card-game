package org.agoncal.application.ui;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.application.model.Game;
import org.agoncal.application.service.CardGameService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/play")
@ApplicationScoped
public class CardGamePage {

  @Inject
  CardGameService service;

  Game game;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance play(Game game);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance playANewGame(@QueryParam("one") String namePlayerOne, @QueryParam("two") String namePlayerTwo) {
    game = service.startANewGame(namePlayerOne, namePlayerTwo);
    return Templates.play(game);
  }

  @Path("/next")
  @GET
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance nextCard(@QueryParam("deck_id") String deckId) {
    return Templates.play(service.playOneCard(game));
  }
}
