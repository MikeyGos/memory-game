package pl.memorygameszkola;

import pl.memorygameszkola.stockOfCard.Card;
import pl.memorygameszkola.stockOfCard.CardDeck;

public class TestFunctionCard {
    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();

        cardDeck.shuffle();
        System.out.println(cardDeck);
    }
}
