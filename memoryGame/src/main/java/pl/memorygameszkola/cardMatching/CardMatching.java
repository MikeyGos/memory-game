package pl.memorygameszkola.cardMatching;

import pl.memorygameszkola.stockOfCard.Card;

public class CardMatching extends Card {

    private boolean matched;

    public CardMatching(String cardName, boolean matched) {
        super(cardName);
        this.matched = false;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean sameCard(CardMatching secondCard){
        return (this.getCardName().equals(secondCard.getCardName()));
    }
}
