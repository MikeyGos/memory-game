package pl.memorygameszkola.stockOfCard;

import java.util.Arrays;
import java.util.List;

public class Card {

    private String cardName;

    public Card(String cardName) {
        setCardName(cardName);
    }

    public String getCardName() {
        return cardName;
    }

    public List<String> getCardNameList() {
        return Arrays.asList("hiena", "lew", "mufasa", "pumba", "rafiki", "skaza", "timon", "zazu");
    }

    public void setCardName(String cardName) {
        if (cardName == null) {
            throw new NullPointerException("Null to nie nazwa karty");
        }
        cardName = cardName.toLowerCase();
        if (getCardNameList().contains(cardName)) {
            this.cardName = cardName;
        } else {
            throw new IllegalArgumentException(cardName + " nieprawidłowa karta!");
        }
    }

    @Override
    public String toString() {
        return cardName + " działa";
    }
}
