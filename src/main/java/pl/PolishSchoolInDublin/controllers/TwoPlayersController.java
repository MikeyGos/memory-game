package pl.PolishSchoolInDublin.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TwoPlayersController extends BaseMemoryGameController implements Initializable {


    @FXML
    private Label firstPlayerScore;

    @FXML
    private Label secondPlayerScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstScore = 0;
        secondScore = 0;
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
        if(currentPlayer == 1){
            firstScore++;
            firstPlayerScore.setText(Integer.toString(firstScore));
        } else {
            secondScore++;
            secondPlayerScore.setText(Integer.toString(secondScore));
        }
    }
}


