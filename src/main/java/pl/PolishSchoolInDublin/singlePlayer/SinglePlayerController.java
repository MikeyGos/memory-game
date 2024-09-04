package pl.PolishSchoolInDublin.singlePlayer;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import pl.PolishSchoolInDublin.mainControllers.BaseMemoryGameController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SinglePlayerController extends BaseMemoryGameController implements Initializable {

    public Label attempt;
    public Label playerTurnLabel;
    int singlePlayerAttemptScore = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeImageView();
        playAgain();
    }

    @Override
    protected void playAgain() {
        singlePlayerAttemptScore = 0;
        numbOfAttempt = 0;
        firstScore = 0;
        super.playAgain();
    }

    @Override
    protected void nextTurn() {
    }

    @Override
    protected void attemptScoreAdd() {
        attemptScore.setText(String.valueOf(numbOfAttempt));
        singlePlayerAttemptScore = numbOfAttempt;
    }

    @Override
    protected Alert getAlert() {
        String winner = "";
        if (firstScore == 6) {
            winner = "Udało Ci się w ciągu " + singlePlayerAttemptScore + " ruchów";
        }
        return getAlert(winner);
    }




}