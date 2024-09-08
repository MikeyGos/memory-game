module pl.PolishSchoolInDublin {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.PolishSchoolInDublin to javafx.fxml;
    exports pl.PolishSchoolInDublin;
    exports pl.PolishSchoolInDublin.stockOfCard;
    opens pl.PolishSchoolInDublin.stockOfCard to javafx.fxml;
    opens pl.PolishSchoolInDublin.mainControllers to javafx.fxml;
    exports pl.PolishSchoolInDublin.mainControllers;
    exports pl.PolishSchoolInDublin.matching;
    opens pl.PolishSchoolInDublin.matching to javafx.fxml;
    exports pl.PolishSchoolInDublin.singlePlayer;
    opens pl.PolishSchoolInDublin.singlePlayer to javafx.fxml;
    exports pl.PolishSchoolInDublin.twoPlayers;
    opens pl.PolishSchoolInDublin.twoPlayers to javafx.fxml;
    exports pl.PolishSchoolInDublin.session;
    opens pl.PolishSchoolInDublin.session to javafx.fxml;
}