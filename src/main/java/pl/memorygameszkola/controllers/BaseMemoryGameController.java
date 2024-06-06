package pl.memorygameszkola.controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.memorygameszkola.Card;
import pl.memorygameszkola.MenuMemoryGame;
import pl.memorygameszkola.matching.CardMatching;
import pl.memorygameszkola.stockOfCard.CardDeck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public abstract class BaseMemoryGameController {

    protected ArrayList<CardMatching> usedCardDeck;
    protected CardMatching firstCard, secondCard;
    protected int numbOfAttempt;
    protected int firstCardIndex;
    protected int secondCardIndex;
    protected int currentPlayer = 1;
    protected int firstScore = 0;
    protected int secondScore = 0;

    @FXML
    protected Label attemptScore;

    @FXML
    protected FlowPane imageFlowPane;
    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuMemoryGame.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void playAgain() {
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
        updatePlayerTurn();
    }


    protected void initializeImageView() {
        for (int i = 0; i < imageFlowPane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
            imageView.setImage(new Card().getQuestionMark());
            imageView.setUserData(i);

            imageView.setOnMouseClicked(mouseEvent -> switchCard((int) imageView.getUserData()
            ));
        }
    }


    protected void switchAllCards() {
        for (int i = 0; i < imageFlowPane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
            CardMatching card = usedCardDeck.get(i);

            if (card.isMatched()) {
                imageView.setImage(card.getImage());
            } else {
                imageView.setImage(card.getQuestionMark());
            }
        }
    }


    protected void switchCard(int indexCard) {
        if (firstCard == null && secondCard == null) {
            switchAllCards();
        }
        ImageView image = (ImageView) imageFlowPane.getChildren().get(indexCard);
        if (firstCard == null) {
            firstCard = usedCardDeck.get(indexCard);
            firstCardIndex = indexCard;
            image.setImage(firstCard.getImage());

        } else if (secondCard == null) {
            secondCard = usedCardDeck.get(indexCard);
            secondCardIndex = indexCard;
            image.setImage(secondCard.getImage());
            checkMatched();

            attemptScoreAdd();
        }
    }

    protected void attemptScoreAdd() {
        attemptScore.setText(String.valueOf(numbOfAttempt));

    }

    protected void checkMatched() {
        if (firstCardIndex == secondCardIndex) {
            secondCard = null;
        } else if (firstCard.sameCard(secondCard)) {
            numbOfAttempt++;
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            firstCard = null;
            secondCard = null;
            score();
        } else {
            numbOfAttempt++;
            PauseTransition pause = getPauseTransition();
            pause.play();
        }
    }


    protected PauseTransition getPauseTransition() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            ImageView firstImage = (ImageView) imageFlowPane.getChildren().get(firstCardIndex);
            ImageView secondImage = (ImageView) imageFlowPane.getChildren().get(secondCardIndex);
            firstImage.setImage(firstCard.getQuestionMark());
            secondImage.setImage(secondCard.getQuestionMark());
            firstCard = null;
            secondCard = null;
            nextTurn();
        });
        return pause;
    }

    protected void score() {
        if (currentPlayer == 1) {
            firstScore++;
        } else {
            secondScore++;
        }
    }

    protected void nextTurn() {
        currentPlayer = currentPlayer == 1 ? 2 : 1;
        updatePlayerTurn();
    }

    protected void updatePlayerTurn() {
        if (currentPlayer == 1) {
            imageFlowPane.setStyle("-fx-background-color: Blue");
        } else {
            imageFlowPane.setStyle("-fx-background-color: RED");
        }
    }

}
