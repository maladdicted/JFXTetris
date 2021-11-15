module com.grandrain.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports com.grandrain.tetris.views;
    opens com.grandrain.tetris.views to javafx.fxml;
    exports com.grandrain.tetris;
    opens com.grandrain.tetris to javafx.fxml;
}