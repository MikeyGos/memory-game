module pl.memorygameszkola.memorygame {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.PolishSchoolInDublin to javafx.fxml;
    exports pl.PolishSchoolInDublin;
    exports pl.PolishSchoolInDublin.stockOfCard;
    opens pl.PolishSchoolInDublin.stockOfCard to javafx.fxml;
    opens pl.PolishSchoolInDublin.controllers to javafx.fxml;
    exports pl.PolishSchoolInDublin.controllers;
    exports pl.PolishSchoolInDublin.matching;
    opens pl.PolishSchoolInDublin.matching to javafx.fxml;
}