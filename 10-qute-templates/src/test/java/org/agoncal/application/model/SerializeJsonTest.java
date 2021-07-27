package org.agoncal.application.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@QuarkusTest
class SerializeJsonTest {

  private final String json = "{\n" +
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
  public void shouldSerializeJson() {
    Jsonb jsonb = JsonbBuilder.create();
    Deck deck = jsonb.fromJson(json, Deck.class);
    Assertions.assertEquals(2, deck.getCards().size());
    Assertions.assertEquals(0, deck.getRemaining());
    Assertions.assertEquals("wd8ttfw18vze", deck.getId());
  }
}
