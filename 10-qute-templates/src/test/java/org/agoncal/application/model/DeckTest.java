package org.agoncal.application.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.agoncal.application.model.Suit.HEARTS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class DeckTest {

  private final String deckOneJack = "{\n" +
    "\"success\": true,\n" +
    "\"deck_id\": \"wd8ttfw18vze\",\n" +
    "\"cards\": [\n" +
    "{\n" +
    "\"code\": \"JH\",\n" +
    "\"image\": \"https://deckofcardsapi.com/static/img/JH.png\",\n" +
    "\"images\": {\n" +
    "\"svg\": \"https://deckofcardsapi.com/static/img/JH.svg\",\n" +
    "\"png\": \"https://deckofcardsapi.com/static/img/JH.png\"\n" +
    "},\n" +
    "\"value\": \"JACK\",\n" +
    "\"suit\": \"HEARTS\"\n" +
    "}\n" +
    "],\n" +
    "\"remaining\": 51\n" +
    "}";

  private final String deckTwoCards = "{\n" +
    "\"success\": true,\n" +
    "\"deck_id\": \"wd8ttfw18vze\",\n" +
    "\"cards\": [\n" +
    "{\n" +
    "\"code\": \"JH\",\n" +
    "\"image\": \"https://deckofcardsapi.com/static/img/JH.png\",\n" +
    "\"images\": {\n" +
    "\"svg\": \"https://deckofcardsapi.com/static/img/JH.svg\",\n" +
    "\"png\": \"https://deckofcardsapi.com/static/img/JH.png\"\n" +
    "},\n" +
    "\"value\": \"JACK\",\n" +
    "\"suit\": \"HEARTS\"\n" +
    "},\n" +
    "{\n" +
    "\"code\": \"8S\",\n" +
    "\"image\": \"https://deckofcardsapi.com/static/img/8S.png\",\n" +
    "\"images\": {\n" +
    "\"svg\": \"https://deckofcardsapi.com/static/img/8S.svg\",\n" +
    "\"png\": \"https://deckofcardsapi.com/static/img/8S.png\"\n" +
    "},\n" +
    "\"value\": \"8\",\n" +
    "\"suit\": \"SPADES\"\n" +
    "}\n" +
    "],\n" +
    "\"remaining\": 0\n" +
    "}";

  @Test
  public void shouldSerializeTwoCards() {
    Jsonb jsonb = JsonbBuilder.create();
    Deck deck = jsonb.fromJson(deckTwoCards, Deck.class);
    assertEquals(2, deck.getCards().size());
    assertEquals(0, deck.getRemaining());
    assertEquals("wd8ttfw18vze", deck.getId());
  }

  @Test
  public void shouldSerializeOneJack() {
    Jsonb jsonb = JsonbBuilder.create();
    Deck deck = jsonb.fromJson(deckOneJack, Deck.class);
    assertEquals(1, deck.getCards().size());
    assertEquals("JH", deck.getCards().get(0).getCode());
    assertEquals(HEARTS, deck.getCards().get(0).getSuit());
    assertEquals("JACK", deck.getCards().get(0).getValue());
    assertEquals("https://deckofcardsapi.com/static/img/JH.png", deck.getCards().get(0).getImage());
    assertEquals(51, deck.getRemaining());
    assertEquals("wd8ttfw18vze", deck.getId());
  }
}
