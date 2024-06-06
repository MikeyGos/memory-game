module pl.memorygameszkola.memorygame {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.memorygameszkola to javafx.fxml;
    exports pl.memorygameszkola;
    exports pl.memorygameszkola.stockOfCard;
    opens pl.memorygameszkola.stockOfCard to javafx.fxml;
    opens pl.memorygameszkola.controllers to javafx.fxml;
    exports pl.memorygameszkola.controllers;
    exports pl.memorygameszkola.matching;
    opens pl.memorygameszkola.matching to javafx.fxml;
}