package pl.PolishSchoolInDublin.mainControllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class PlayerVsComputerController extends BaseMemoryGameController implements Initializable {

    private Random random = new Random();
    private int firstCardIndex;
    private int secondCardIndex;

    @FXML
    private Label firstPlayerScore;

    @FXML
    private Label secondPlayerScore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstScore = 0;
        secondScore = 0;
        initializeImageView();
        playAgain();
    }

    @Override
    protected void playAgain() {
        firstScore = 0;
        secondScore = 0;
        currentPlayer = 1;
        firstPlayerScore.setText(Integer.toString(firstScore));
        secondPlayerScore.setText(Integer.toString(secondScore));
        super.playAgain();
        disableUserInteraction(false);
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

    public PlayerVsComputerController() {
        // Usunięcie HashMap knownCards
    }

    private void getFirstCardIndex() {
        do {
            firstCardIndex = random.nextInt(usedCardDeck.size());
        } while (usedCardDeck.get(firstCardIndex).isMatched());
    }

    private void getSecondCardIndex() {
        do {
            secondCardIndex = random.nextInt(usedCardDeck.size());
        } while (secondCardIndex == firstCardIndex || usedCardDeck.get(secondCardIndex).isMatched());
    }

    public void computerMove() {
        disableUserInteraction(true);
        selectCardsBasedOnRandomDifficulty(); // Wybierz karty w zależności od losowej liczby
        switchCard(firstCardIndex);
        PauseTransition pauseAfterFirstCard = new PauseTransition(Duration.seconds(0.5));
        pauseAfterFirstCard.setOnFinished(event -> {
            switchCard(secondCardIndex);
        });
        pauseAfterFirstCard.play();
    }

    @Override
    protected void checkMatched() {
        if (firstCard == secondCard) {
            secondCard = null;
        } else if (firstCard.sameCard(secondCard)) {
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            resetSelectedCard();
            numbOfAttempt++;
            PauseTransition pauseTransition = getPauseTransition();
            pauseTransition.play();
            score();
            if (currentPlayer == 1) {
                if (allCardsMatched()) {
                    showWinningMessage();
                }
            }
        } else {
            numbOfAttempt++;
            PauseTransition pause = getPauseTransition();
            pause.play();
        }
    }

    @Override
    public PauseTransition getPauseTransition() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            if (firstCard != null && secondCard != null) {
                ImageView firstImage = (ImageView) imageFlowPane.getChildren().get(firstCardIndex);
                ImageView secondImage = (ImageView) imageFlowPane.getChildren().get(secondCardIndex);
                firstImage.setImage(firstCard.getQuestionMark());
                secondImage.setImage(secondCard.getQuestionMark());
                resetSelectedCard();
                nextTurn();
            } else if (currentPlayer == 2) {
                if (allCardsMatched()) {
                    Platform.runLater(this::showWinningMessage);        // can't be pause - animation error
                } else {
                    computerMove();
                }
            }
        });
        return pause;
    }

    @Override
    protected void nextTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        updatePlayerTurn();
        disableUserInteraction(false);
        if (currentPlayer == 2) {
            disableUserInteraction(true);
            computerMove();
        }
    }

    protected void disableUserInteraction(boolean disable) {
        for (Node node : imageFlowPane.getChildren()) {
            node.setDisable(disable);
        }
    }

    @Override
    protected Alert getAlert() {
        String winner;
        if (firstScore > secondScore) {
            winner = "Wygrałeś!!";
        } else if (secondScore > firstScore) {
            winner = "Komputer wygrał!!";
        } else {
            winner = "Remis!";
        }
        return getAlert(winner);
    }

    private void selectCardsBasedOnRandomDifficulty() {
        int randomValue = random.nextInt(11);
        System.out.println(randomValue);
        if (randomValue > 6) {
            selectBestKnownFirstCard();
            findKnownCardPair();
        } else {
            getFirstCardIndex();
            getSecondCardIndex();
        }
    }

    private void findKnownCardPair() {
        for (int i = 0; i < usedCardDeck.size(); i++) {
            if (!usedCardDeck.get(i).isMatched()) {
                String cardName = usedCardDeck.get(i).getCardName();
                for (int j = i + 1; j < usedCardDeck.size(); j++) {
                    if (!usedCardDeck.get(j).isMatched() && usedCardDeck.get(j).getCardName().equals(cardName)) {
                        firstCardIndex = i;
                        secondCardIndex = j;
                        return;
                    }
                }
            }
        }
    }

    private void selectBestKnownFirstCard() {
        for (int i = 0; i < usedCardDeck.size(); i++) {
            if (!usedCardDeck.get(i).isMatched()) {
                firstCardIndex = i;
                return;
            }
        }
        getFirstCardIndex();
    }
}