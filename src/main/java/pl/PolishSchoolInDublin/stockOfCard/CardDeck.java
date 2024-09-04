package pl.PolishSchoolInDublin.stockOfCard;

import pl.PolishSchoolInDublin.Card;

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
        if(!cardDeck.isEmpty()){
            return cardDeck.removeFirst();
        } else
            throw new NullPointerException("Error on cardDeck TopCard");
    }
}