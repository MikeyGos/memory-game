package pl.PolishSchoolInDublin;

import javafx.scene.image.Image;
import pl.PolishSchoolInDublin.session.SessionManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Card {

    private String cardName;
    List<String> fruit = Arrays.asList("ananas", "arbuz", "banan", "cytryna", "gruszka", "jablko", "kiwi", "pomarancza", "truskawka");
    List<String> animal = Arrays.asList("hiena", "lew", "mufasa", "pumba", "rafiki", "skaza", "timon", "zazu");
    List<String> tails = Arrays.asList("kafelek1","kafelek2","kafelek3","kafelek4","kafelek5","kafelek6","kafelek7","kafelek8","kafelek9");
    public Card() {}

    public Card(String cardName) {
        setCardName(cardName);
    }

    public String getCardName() {
        return cardName;
    }

    public List<String> getCardNameList() {
        SessionManager session = SessionManager.getInstance();
        if (session.isFruitSelected()) {
            return fruit;
        } else if (session.isAnimalSelected()) {
            return animal;
        } else if (session.isTailsSelected()) {
            return tails;
        }
        return tails;
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

    public String pathName() {
        return "imagesCard/" + cardName + ".jpg";
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Card.class.getResourceAsStream(pathName())));
    }

    public Image getQuestionMark() {
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

    @Override
    public String toString() {
        return cardName;
    }
}