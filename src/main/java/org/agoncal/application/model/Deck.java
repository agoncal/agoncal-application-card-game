package org.agoncal.application.model;
/*
 * A deck of cards.
 */

import java.util.Random;

import static org.agoncal.application.model.Suit.CLUBS;
import static org.agoncal.application.model.Suit.DIAMONDS;
import static org.agoncal.application.model.Suit.HEARTS;
import static org.agoncal.application.model.Suit.SPADES;

public class Deck {

  // ======================================
  // =             Attributes             =
  // ======================================

  private Card cards[]; // An array of Card objects
  private int next; // Holds position of next card to be dealt
  public static final int NUMBER_OF_CARDS = 52;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Deck() {
    createDeck();
    shuffleDeck();
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public Card dealOneCard() { // Deals one card at a time
    Card c = cards[next];
    next++;
    return c;
  }

  private void createDeck() {
    cards = new Card[NUMBER_OF_CARDS+1]; // Indices 1-52 (does not use index 0)

    // Fill the deck with cards
    for (int rank = 1; rank <= 13; rank++) {
      // Place cards in order in deck, shuffle later
      cards[rank] = new Card(CLUBS, rank); // First suit, ex: 3 of clubs
      cards[rank + 13] = new Card(DIAMONDS, rank); // Second suit, diamonds
      cards[rank + 26] = new Card(HEARTS, rank); // Third suit, hearts
      cards[rank + 39] = new Card(SPADES, rank); // Fourth suit, spades
    }

    next = 1; // Set next to 1 since first card is in index 1

  }
  private void shuffleDeck() {
    Random randomNumber = new Random();

    for (int card = 1; card <= NUMBER_OF_CARDS; card++) {
      // Find a random place in the deck
      int rand = randomNumber.nextInt(NUMBER_OF_CARDS) + 1;

      // Swap cards in deck
      Card temp = cards[card]; // Card from random position
      cards[card] = cards[rand];
      cards[rand] = temp;
    }

    next = 1; // Reset next
  }

  Card[] getCards() {
    return cards;
  }

  int getNext() {
    return next;
  }
}
