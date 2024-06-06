package pl.memorygameszkola.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.memorygameszkola.MenuMemoryGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuMemoryGameController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    void singlePlayer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuMemoryGame.class.getResource("singlePlayer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void twoPlayers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuMemoryGame.class.getResource("twoPlayers-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

}