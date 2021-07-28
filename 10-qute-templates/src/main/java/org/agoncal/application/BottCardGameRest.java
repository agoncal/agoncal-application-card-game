package org.agoncal.application;

import org.agoncal.application.model.Game;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class BottCardGameRest {

  @Inject
  BottCardGame service;

  Game game;

  @POST
  public Game playANewGame(@QueryParam("one") String namePlayerOne, @QueryParam("two") String namePlayerTwo) {
    game = service.startANewGame(namePlayerOne, namePlayerTwo);
    return game;
  }

  @GET
  @Path("/{deck_id}")
  public Game nextCard(@PathParam("deck_id") String deckId) {
    return service.playOneCard(game);
  }
}
