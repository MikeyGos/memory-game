package pl.PolishSchoolInDublin;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Card {

    private String cardName;

    public Card() {
    }

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
        return cardName;
    }
    public String pathName(){
        String pathName;
        return pathName = "imagesCard/" + cardName +".jpg";
    }
    public Image getImage(){
        return new Image(Objects.requireNonNull(Card.class.getResourceAsStream(pathName())));
    }
    public Image getQuestionMark(){
        return new Image(Objects.requireNonNull(Card.class.getResourceAsStream("imagesCard/znakZapytania.jpg")));
    }

    public Image getRandomCard() {
        List<String> cardNameList = getCardNameList();
        Collections.shuffle(cardNameList);
        String randomCardName = cardNameList.getFirst();
        String randomCardPath = "imagesCard/" + randomCardName + ".jpg";
        return new Image(Objects.requireNonNull(Card.class.getResourceAsStream(randomCardPath)));
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;

        return Objects.equals(getCardName(), card.getCardName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCardName());
    }
}