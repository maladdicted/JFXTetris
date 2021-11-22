package com.grandrain.tetris.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameOverPane  extends BorderPane {

    public GameOverPane() {
        final Label gameOverLabel = new Label("КІНЕЦЬ ГРИ");
        gameOverLabel.getStyleClass().add("game-over");
        setCenter(gameOverLabel);
    }

}
