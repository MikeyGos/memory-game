package pl.PolishSchoolInDublin.mainControllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

public class PlayerVsComputerController extends BaseMemoryGameController implements Initializable {

    private DifficultyLevel difficultyLevel;
    private final Map<Integer, String> knownCards;
    private Random random = new Random();
    private int firstCardIndex;

    public enum DifficultyLevel {
        EASY, MEDIUM, HARD
    }

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
        this.difficultyLevel = DifficultyLevel.EASY;
        this.knownCards = new HashMap<>();
    }

    private void getFirstCardIndex() {

        do {
            firstCardIndex = random.nextInt(usedCardDeck.size());
        } while (usedCardDeck.get(firstCardIndex).isMatched());
        System.out.println(firstCardIndex);
    }

    private void getSecondCardIndex() {
        do {
            secondCardIndex = random.nextInt(usedCardDeck.size());
        } while (secondCardIndex == firstCardIndex || usedCardDeck.get(secondCardIndex).isMatched());
        System.out.println(secondCardIndex);
    }

    public void computerMove() {
        disableUserInteraction(true);
        getFirstCardIndex();
        getSecondCardIndex();

        switchCard(firstCardIndex);
        PauseTransition pauseAfterFirstCard = new PauseTransition(Duration.seconds(1));
        pauseAfterFirstCard.setOnFinished(event -> {
            switchCard(secondCardIndex);
        });
        pauseAfterFirstCard.play();
    }

    @Override
    protected void checkMatched() {
        if (firstCard.sameCard(secondCard)) {
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            resetSelectedCard();
            numbOfAttempt++;
            PauseTransition pauseTransition = getPauseTransition();
            pauseTransition.play();
            score();
            if (currentPlayer == 1) {       // have to be checked player, animation error
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
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
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

    public void startGameVsComputer(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        playAgain();
    }

    protected void disableUserInteraction(boolean disable) {
        for (Node node : imageFlowPane.getChildren()) {
            node.setDisable(disable);
        }
    }

}