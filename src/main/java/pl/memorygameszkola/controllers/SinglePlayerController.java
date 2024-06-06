package pl.memorygameszkola.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SinglePlayerController extends BaseMemoryGameController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeImageView();

        playAgain();
    }

    @Override
    protected void nextTurn() {

    }
}
