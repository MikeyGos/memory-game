package pl.memorygameszkola;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TwoPlayersApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SinglePlayerApp.class.getResource("twoPlayers-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Memory Game!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

