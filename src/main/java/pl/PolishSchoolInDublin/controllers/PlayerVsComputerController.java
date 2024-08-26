package pl.PolishSchoolInDublin.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class PlayerVsComputerController extends BaseMemoryGameController implements Initializable {
    private Random random = new Random();

    @FXML
    private Label firstPlayerScore;

    @FXML
    private Label secondPlayerScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeImageView();
        playAgain();
    }

    @Override
    protected void playAgain() {
        firstScore = 0;
        secondScore = 0;
        firstPlayerScore.setText(Integer.toString(firstScore));
        secondPlayerScore.setText(Integer.toString(secondScore));
        super.playAgain();
    }

    @Override
    protected void score() {
        if (currentPlayer == 1) {
            firstScore++;
            firstPlayerScore.setText(Integer.toString(firstScore));
        } else {
            secondScore++;
            secondPlayerScore.setText(Integer.toString(secondScore));
        }
    }

    @Override
    protected void nextTurn() {
        currentPlayer = currentPlayer == 1 ? 2 : 1;
        updatePlayerTurn();
        if (currentPlayer == 2) {
            startComputerMove();
        }
    }

    @Override
    protected void checkMatched() {
        if (firstCardIndex == secondCardIndex) {
            secondCard = null;
        } else if (firstCard.sameCard(secondCard)) {
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            numbOfAttempt++;
            score();
            if (allCardsMatched()) {
                showWinningMessage();
            } else {
                if (currentPlayer == 2) {
                    PauseTransition nextTurnPause = getPauseTransition();
                    nextTurnPause.setOnFinished(event -> startComputerMove());
                    nextTurnPause.play();
                }
            }
            firstCard = null;
            secondCard = null;
        } else {
            numbOfAttempt++;
            PauseTransition pause = getPauseTransition();
            pause.play();
        }
    }

    @Override
    protected void updatePlayerTurn() {
        if (currentPlayer == 1) {
            imageFlowPane.setStyle("-fx-background-color: Blue");
        } else {
            imageFlowPane.setStyle("-fx-background-color: GREEN");
        }
    }

    private void startComputerMove() {
        disableUserInteraction(true);
        if (allCardsMatched()) {
            disableUserInteraction(false);
            return;
        }
        firstCard = null;
        secondCard = null;
        selectFirstCard();
    }
    private void selectFirstCard() {
        int firstIndex = getRandomUnmatchedCardIndex();
        switchCard(firstIndex);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> selectSecondCard());
        pause.play();
    }
    private void selectSecondCard() {
        int secondIndex = getRandomUnmatchedCardIndex();
        switchCard(secondIndex);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> handleMatchResult());
        pause.play();
    }

    private void handleMatchResult() {
        if (firstCard != null && secondCard != null) {
            if (firstCard.isMatched() && secondCard.isMatched()) {
                score();
                if (!allCardsMatched()) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> startComputerMove());
                    pause.play();
                } else {
                    showWinningMessage();
                }
            } else {
                PauseTransition nextTurnPause = getPauseTransition();
                nextTurnPause.setOnFinished(event -> endComputerMove());
                nextTurnPause.play();
            }
        } else {
            PauseTransition nextTurnPause = getPauseTransition();
            nextTurnPause.setOnFinished(event -> endComputerMove());
            nextTurnPause.play();
        }
    }

    private void endComputerMove() {
        firstCard = null;
        secondCard = null;
        currentPlayer = 1;
        updatePlayerTurn();
        disableUserInteraction(false);
    }

    protected void disableUserInteraction(boolean disable) {
        for (Node node : imageFlowPane.getChildren()) {
            node.setDisable(disable);
        }
    }

    private int getRandomUnmatchedCardIndex() {
        int index;
        do {
            index = random.nextInt(usedCardDeck.size());
        } while (usedCardDeck.get(index).isMatched());
        return index;
    }
}