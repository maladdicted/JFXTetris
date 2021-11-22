module com.grandrain.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.datatransfer;
    requires java.desktop;

    exports com.grandrain.tetris.logic;
    exports com.grandrain.tetris.logic.events;
    exports com.grandrain.tetris.logic.bricks;
    exports com.grandrain.tetris.logic.rotator;
    exports com.grandrain.tetris.views;
    opens com.grandrain.tetris.views to javafx.graphics;
    exports com.grandrain.tetris.controllers;
    opens com.grandrain.tetris.controllers to javafx.fxml;
    exports com.grandrain.tetris.gui;
    opens com.grandrain.tetris.gui to javafx.fxml;
}