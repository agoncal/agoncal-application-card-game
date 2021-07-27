package org.agoncal.application.service.apis;

import org.agoncal.application.model.Card;

import javax.json.bind.annotation.JsonbProperty;
import java.util.ArrayList;
import java.util.List;

public class DeckOfCards {

  // ======================================
  // =             Attributes             =
  // ======================================

  private Boolean success;
  @JsonbProperty("deck_id")
  private String deckId;
  private Integer remaining;
  private Boolean shuffled;
  private List<Card> cards = new ArrayList<>();

  // ======================================
  // =            Constructors            =
  // ======================================

  // ======================================
  // =              Methods               =
  // ======================================

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getDeckId() {
    return deckId;
  }

  public void setDeckId(String deckId) {
    this.deckId = deckId;
  }

  public Integer getRemaining() {
    return remaining;
  }

  public void setRemaining(Integer remaining) {
    this.remaining = remaining;
  }

  public Boolean getShuffled() {
    return shuffled;
  }

  public void setShuffled(Boolean shuffled) {
    this.shuffled = shuffled;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }
}
