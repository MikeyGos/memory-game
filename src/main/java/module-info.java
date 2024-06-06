module pl.memorygameszkola.memorygame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens pl.memorygameszkola to javafx.fxml;
    exports pl.memorygameszkola;
    exports pl.memorygameszkola.stockOfCard;
    opens pl.memorygameszkola.stockOfCard to javafx.fxml;
}