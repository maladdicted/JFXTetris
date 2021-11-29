module com.grandrain.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.datatransfer;
    requires java.desktop;
    requires ini4j;

    exports com.grandrain.tetris.logic;
    exports com.grandrain.tetris.logic.events;
    exports com.grandrain.tetris.logic.bricks;
    exports com.grandrain.tetris.logic.rotator;
    exports com.grandrain.tetris;
    opens com.grandrain.tetris to javafx.graphics;
    exports com.grandrain.tetris.controllers;
    opens com.grandrain.tetris.controllers to javafx.fxml;
    exports com.grandrain.tetris.gui;
    opens com.grandrain.tetris.gui to javafx.fxml;
}