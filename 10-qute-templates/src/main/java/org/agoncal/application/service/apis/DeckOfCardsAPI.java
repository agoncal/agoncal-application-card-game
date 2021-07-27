package org.agoncal.application.service.apis;

import org.agoncal.application.service.LoggingFilter;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RegisterRestClient//(configKey = "deckofcards-api")
@Path("/api/deck")
@RegisterProvider(LoggingFilter.class)
public interface DeckOfCardsAPI {

  /**
   * https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1
   * <p>
   * {
   * "success": true,
   * "deck_id": "r0jvo8i4t3pn",
   * "remaining": 52,
   * "shuffled": true
   * }
   */
  @GET
  @Path("/new/shuffle/")
  @Produces(MediaType.APPLICATION_JSON)
  DeckOfCards newDeck(@QueryParam("deck_count") @DefaultValue("1") int deckCount);

  /**
   * https://deckofcardsapi.com/api/deck/heveznyy02y3/draw/?count=1
   * <pre>
   * {
   *   "success": true,
   *   "deck_id": "wd8ttfw18vze",
   *   "cards": [
   *     {
   *       "code": "JH",
   *       "image": "https://deckofcardsapi.com/static/img/JH.png",
   *       "images": {
   *         "svg": "https://deckofcardsapi.com/static/img/JH.svg",
   *         "png": "https://deckofcardsapi.com/static/img/JH.png"
   *       },
   *       "value": "JACK",
   *       "suit": "HEARTS"
   *     },
   *     {
   *       "code": "8S",
   *       "image": "https://deckofcardsapi.com/static/img/8S.png",
   *       "images": {
   *         "svg": "https://deckofcardsapi.com/static/img/8S.svg",
   *         "png": "https://deckofcardsapi.com/static/img/8S.png"
   *       },
   *       "value": "8",
   *       "suit": "SPADES"
   *       }
   *   ],
   *   "remaining": 0
   * }
   * </pre>
   */

  @GET
  @Path("/{deckid}/draw/")
  @Produces(MediaType.APPLICATION_JSON)
  DeckOfCards dealOneCard(@PathParam("deckid") String deckid, @QueryParam("count") @DefaultValue("1") int count);


}
