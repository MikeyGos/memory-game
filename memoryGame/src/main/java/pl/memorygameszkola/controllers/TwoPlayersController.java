package pl.memorygameszkola.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import pl.memorygameszkola.stockOfCard.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class TwoPlayersController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label attempt;

    @FXML
    private FlowPane imageFlowPane;

    @FXML
    private Button playAgainButton;

    @FXML
    private Label scoreFirstPlayer;

    @FXML
    private Label scoreSecondPlayer;


    @FXML
    void playAgain(KeyEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}


