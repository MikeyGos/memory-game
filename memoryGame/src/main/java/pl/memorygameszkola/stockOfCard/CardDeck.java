package pl.memorygameszkola.stockOfCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private ArrayList<Card> cardDeck;

    public CardDeck() {
        this.cardDeck = new ArrayList<>();
        List<String> deck = new Card().getCardNameList();

        for (String card : deck) {
            cardDeck.add(new Card(card));
//            cardDeck.add(new Card(card));
        }
    }
    public void shuffle(){
        Collections.shuffle(cardDeck);
    }

    @Override
    public String toString() {
        return cardDeck.toString();
    }
}
