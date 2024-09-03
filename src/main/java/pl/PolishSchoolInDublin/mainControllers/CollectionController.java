package pl.PolishSchoolInDublin.mainControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.PolishSchoolInDublin.Card;
import pl.PolishSchoolInDublin.session.SessionManager;

import java.io.IOException;

public class CollectionController {

    private Card card;

    @FXML
    private Button fruitButton;

    @FXML
    private Button animalButton;

    @FXML
    public void initialize() {
        card = new Card();
    }

    @FXML
    public void handleFruitClick() {
        // Ustawiamy wybór "fruit" jako true
        SessionManager.getInstance().setFruitSelected(true);
        loadMenu();
    }

    @FXML
    public void handleAnimalClick() {
        // Ustawiamy wybór "animal" jako true
        SessionManager.getInstance().setAnimalSelected(true);
        loadMenu();
    }

    @FXML
    private void loadMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/PolishSchoolInDublin/view/menu-view.fxml"));
            Parent menuRoot = loader.load();

            Stage stage = (Stage) fruitButton.getScene().getWindow();
            stage.setScene(new Scene(menuRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}