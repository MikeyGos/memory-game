package pl.memorygameszkola.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import pl.memorygameszkola.Card;

import java.net.URL;
import java.util.ResourceBundle;

public class SinglePlayerController implements Initializable {

    @FXML
    private Label attempt;

    @FXML
    private Label attemptScore;

    @FXML
    private FlowPane imageFlowPane;

    @FXML
    private ImageView imageView;

    @FXML
    private Button playAgainButton;

    @FXML
    void playAgain(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeImageView();
    }

    private void initializeImageView() {
        for(int i = 0; i < imageFlowPane.getChildren().size();i++)
        {
            ImageView imageView = (ImageView) imageFlowPane.getChildren().get(i);
            imageView.setImage(new Card().getQuestionMark());
            imageView.setUserData(i);

            imageView.setOnMouseClicked(mouseEvent -> System.out.println(imageView.getUserData()));
        }
    }
}

