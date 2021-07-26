# agoncal-application-card-game

## A simple, two player, card game simulation.
#########

The deck of cards contains 52 cards with 13 cards each in the 4 suits:
clubs, diamonds, hearts, spades. Each player begins with 26 cards and one of the players starts the game
by putting their first card on the table. Players take turns by putting the
top card from their hand, until the current card placed on the table matches
the suit of the previous card on the table. If a match happens, the player
whose card matched gets all the cards currently on the table and adds them
at the end of the cards currently in his or her hand. Game continues until
one player gets all 52 cards, or for 10 rounds.

Inspired from https://github.com/mattlevan/Simple_Card_Game
Java Programming: "From the Ground Up" by Bravaco, Simonson

## APIs

### Deck of Cards

http://deckofcardsapi.com

Shuffle

```shell
http://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1
```

Play 2 cards (1 card each player)

```shell
http://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=1
```

http://deckofcardsapi.com/static/img/KH.png
http://deckofcardsapi.com/static/img/8C.png

### DiceBear Avatars

https://avatars.dicebear.com/
https://avatars.dicebear.com/api/bottts/dummy.svg
https://avatars.dicebear.com/api/bottts/dummy.svg?width=300
