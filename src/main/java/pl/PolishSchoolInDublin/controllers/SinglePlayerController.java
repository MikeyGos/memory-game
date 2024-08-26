package pl.PolishSchoolInDublin.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SinglePlayerController extends BaseMemoryGameController implements Initializable {

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
        String winner = "";
        if (firstScore == 6 ){
            winner = "Udało Ci się w ciągu " + singlePlayerAttemptScore + " ruchów";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                winner  + "\n\nCzy chcesz zagrać ponownie?",
                ButtonType.YES,
                ButtonType.NO);

        alert.setHeaderText(null);
        alert.setTitle("Koniec gry");
        return alert;
    }
}
