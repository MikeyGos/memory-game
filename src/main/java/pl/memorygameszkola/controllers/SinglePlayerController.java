package pl.memorygameszkola.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import pl.memorygameszkola.Card;
import pl.memorygameszkola.cardMatching.CardMatching;
import pl.memorygameszkola.stockOfCard.CardDeck;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class SinglePlayerController implements Initializable {

    private ArrayList<CardMatching> usedCardDeck;
    private CardMatching firstCard, secondCard;
    private int numbOfAttempt;
    private int numbOfScore;
    @FXML
    private Label attempt;
    @FXML
    private Label attemptScore;

    @FXML
    private FlowPane imageFlowPane;

    @FXML
    private ImageView imageView;

    @FXML
    private Button playAgainButton;

    @FXML
    void playAgain() {

        firstCard = null;
        secondCard = null;
        numbOfAttempt = 0;
        attemptScore.setText(String.valueOf(0));
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffle();
        usedCardDeck = new ArrayList<>();

        for (int i = 0; i < imageFlowPane.getChildren().size() / 2; i++) {
            Card topCard = cardDeck.topCard();

            usedCardDeck.add(new CardMatching(topCard.getCardName()));
            usedCardDeck.add(new CardMatching(topCard.getCardName()));

        }
        Collections.shuffle(usedCardDeck);
        switchAllCards();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeImageView();
        playAgain();
    }

    private void initializeImageView() {
        for (int i = 0; i < imageFlowPane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
            imageView.setImage(new Card().getQuestionMark());
            imageView.setUserData(i);

            imageView.setOnMouseClicked(mouseEvent -> {
                switchCard((int) imageView.getUserData());

            });
        }
    }


    private void switchAllCards() {
        for (int i = 0; i < imageFlowPane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
            CardMatching card = usedCardDeck.get(i);

            if (card.isMatched())
                imageView.setImage(card.getImage());
            else
                imageView.setImage(card.getQuestionMark());
        }
    }

    private void switchCard(int indexCard) {
        if (firstCard == null && secondCard == null) {
            switchAllCards();
        }
        ImageView image = (ImageView) imageFlowPane.getChildren().get(indexCard);
        if (firstCard == null) {
            firstCard = usedCardDeck.get(indexCard);
            image.setImage(firstCard.getImage());

        } else if (secondCard == null) {
            numbOfAttempt++;
            secondCard = usedCardDeck.get(indexCard);
            image.setImage(secondCard.getImage());
            checkMatched();
            attemptScoreAdd();
        }

    }

    private void attemptScoreAdd() {
        attemptScore.setText(String.valueOf(numbOfAttempt));

    }

    private void checkMatched() {
        if (firstCard.sameCard(secondCard)) {
            firstCard.setMatched(true);
            secondCard.setMatched(true);
        }
            firstCard = null;
            secondCard = null;
    }


}