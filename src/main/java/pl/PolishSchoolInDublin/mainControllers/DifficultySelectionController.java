package pl.PolishSchoolInDublin.mainControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DifficultySelectionController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void selectEasy(ActionEvent event) throws IOException {
        openGameWithDifficulty(event, PlayerVsComputerController.DifficultyLevel.EASY);
    }

    @FXML
    public void selectMedium(ActionEvent event) throws IOException {
        openGameWithDifficulty(event, PlayerVsComputerController.DifficultyLevel.MEDIUM);
    }

    @FXML
    public void selectHard(ActionEvent event) throws IOException {
        openGameWithDifficulty(event, PlayerVsComputerController.DifficultyLevel.HARD);
    }

    private void openGameWithDifficulty(ActionEvent event, PlayerVsComputerController.DifficultyLevel difficulty) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/PolishSchoolInDublin/view/playerVsComputer-view.fxml"));
        root = loader.load();

        // Pobieranie kontrolera PlayerVsComputerController i ustawianie poziomu trudności
        PlayerVsComputerController controller = loader.getController();
        controller.startGameVsComputer(difficulty);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}