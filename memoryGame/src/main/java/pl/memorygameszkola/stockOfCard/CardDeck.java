package pl.memorygameszkola.stockOfCard;

import pl.memorygameszkola.Card;

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
        }
    }
    public void shuffle(){
        Collections.shuffle(cardDeck);
    }

    @Override
    public String toString() {
        return cardDeck.toString();
    }
    public Card topCard(){
        if(cardDeck.size() > 0){
            return cardDeck.remove(0);
        } else
            throw new NullPointerException("Błąd cardDeck topCard");
    }
}