package pl.PolishSchoolInDublin.mainControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.PolishSchoolInDublin.MenuMemoryGame;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuMemoryGameController implements Initializable {
private ResourceBundle resource;
private Locale locale;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void singlePlayer(ActionEvent event) throws IOException {
        loadView("/pl/PolishSchoolInDublin/view/singlePlayer-view.fxml", event);
    }

    @FXML
    void playerVsComputer(ActionEvent event) throws IOException {
        loadView("/pl/PolishSchoolInDublin/view/playerVsComputer-view.fxml", event);
    }

    @FXML
    void twoPlayers(ActionEvent event) throws IOException {
        loadView("/pl/PolishSchoolInDublin/view/twoPlayers-view.fxml", event);
    }

    @FXML
    void collection(ActionEvent event) throws IOException {
        loadView("/pl/PolishSchoolInDublin/view/choice-view.fxml", event);
    }

    private void loadView(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuMemoryGame.class.getResource(fxmlPath));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}