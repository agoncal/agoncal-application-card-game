package org.agoncal.application.service;

import org.agoncal.application.model.Deck;

public interface DeckService {

  Deck getNewShuffledDeck();

  Deck dealOneCard(String deckid);
}
