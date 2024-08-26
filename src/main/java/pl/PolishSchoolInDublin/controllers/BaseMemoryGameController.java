package pl.PolishSchoolInDublin.controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.PolishSchoolInDublin.Card;
import pl.PolishSchoolInDublin.MenuMemoryGame;
import pl.PolishSchoolInDublin.matching.CardMatching;
import pl.PolishSchoolInDublin.stockOfCard.CardDeck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public abstract class BaseMemoryGameController {

    protected ArrayList<CardMatching> usedCardDeck;
    protected CardMatching firstCard, secondCard;
    protected int numbOfAttempt = 1;
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
    void backToMenu(ActionEvent event) throws IOException { //back to menu bottom
        FXMLLoader fxmlLoader = new FXMLLoader(MenuMemoryGame.class.getResource("/pl/PolishSchoolInDublin/view/menu-view.fxml"));
        Stage stage;
        if (event != null) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            stage = (Stage) imageFlowPane.getScene().getWindow();
        }
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @FXML
    protected void playAgain() { // play again button
        firstCard = null;
        secondCard = null;
        numbOfAttempt = 1;
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

    protected void initializeImageView() {                          //initialize image view for cards
        for (int i = 0; i < imageFlowPane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
            imageView.setImage(new Card().getQuestionMark());
            imageView.setUserData(i);

            imageView.setOnMouseClicked(mouseEvent -> switchCard((int) imageView.getUserData()));
        }
    }

    protected void switchAllCards() {       //switch card if is matched or not
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
        if (usedCardDeck.get(indexCard).isMatched()) {
            return;
        }
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

            attemptScoreAdd();
            checkMatched();
        }
    }

    protected void attemptScoreAdd() {      //added score
        attemptScore.setText(String.valueOf(numbOfAttempt));
    }


    public PauseTransition getPauseTransition() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            if (firstCard != null && secondCard != null) {
                ImageView firstImage = (ImageView) imageFlowPane.getChildren().get(firstCardIndex);
                ImageView secondImage = (ImageView) imageFlowPane.getChildren().get(secondCardIndex);
                firstImage.setImage(firstCard.getQuestionMark());
                secondImage.setImage(secondCard.getQuestionMark());
                firstCard = null;
                secondCard = null;
                nextTurn();
            }
        });
        return pause;
    }

    protected void score() {        //add score to current player
        if (currentPlayer == 1) {
            firstScore++;
        } else {
            secondScore++;
        }
    }

    protected void nextTurn() {     //chose player for next round
        currentPlayer = currentPlayer == 1 ? 2 : 1;
        updatePlayerTurn();
    }

    protected void updatePlayerTurn() {     //change player color
        if (currentPlayer == 1) {
            imageFlowPane.setStyle("-fx-background-color: Blue");
        } else {
            imageFlowPane.setStyle("-fx-background-color: RED");
        }
    }


    protected void checkMatched() {
        if (firstCardIndex == secondCardIndex) {
            secondCard = null;
        } else if (firstCard.sameCard(secondCard)) {
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            firstCard = null;
            secondCard = null;
            numbOfAttempt++;
            PauseTransition pauseTransition = getPauseTransition();
            pauseTransition.play();
            score();
            if (allCardsMatched()) {
                showWinningMessage();
            }
        } else {
            numbOfAttempt++;
            PauseTransition pause = getPauseTransition();
            pause.play();
        }
    }

    protected boolean allCardsMatched() {
        for (CardMatching card : usedCardDeck) {
            if (!card.isMatched()) {
                return false;
            }
        }
        return true;
    }

    protected void showWinningMessage() {
        Alert alert = getAlert();

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                playAgain();
            } else {
                try {
                    backToMenu(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Alert getAlert() {
        String winner;
        if (firstScore > secondScore) {
            winner = "Gracz 1 wygrywa!";
        } else if (secondScore > firstScore) {
            winner = "Gracz 2 wygrywa!";
        } else {
            winner = "Remis!";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                winner + "\n\nCzy chcesz zagrać ponownie?",
                ButtonType.YES,
                ButtonType.NO);

        alert.setHeaderText(null);
        alert.setTitle("Koniec gry");
        return alert;
    }

}