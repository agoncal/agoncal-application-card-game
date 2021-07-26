package org.agoncal.application.ui;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.application.model.Game;
import org.agoncal.application.service.CardGameService;

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
public class CardGamePage {

  @Inject
  CardGameService service;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance play(Game game);
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance get(@QueryParam("one") @DefaultValue(NAME_PLAYER_ONE) String namePlayerOne, @QueryParam("two") @DefaultValue(NAME_PLAYER_TWO) String namePlayerTwo) {
    return Templates.play(service.play(namePlayerOne, namePlayerTwo));
  }
}
