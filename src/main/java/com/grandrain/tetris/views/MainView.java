package com.grandrain.tetris.views;

import com.grandrain.tetris.controllers.GameController;
import com.grandrain.tetris.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 510);
        MainController c = fxmlLoader.getController();
        stage.getIcons().add(new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/com/grandrain/tetris/images/icon.png")
        )));
        stage.setTitle("Тетріс");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        new GameController(c);
    }

    public static void main(String[] args) {
        launch();
    }
}