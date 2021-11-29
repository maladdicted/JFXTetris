package com.grandrain.tetris;

import com.grandrain.tetris.controllers.GameController;
import com.grandrain.tetris.controllers.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainController c = load("main", "Тетріс", stage).getController();

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        new GameController(c);
    }

    public static FXMLLoader load(String view, String title, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(
                "/com/grandrain/tetris/views/" + view + "-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        stage.getIcons().add(new Image(Objects.requireNonNull(
                App.class.getResourceAsStream("/com/grandrain/tetris/images/icon.png")
        )));

        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        return fxmlLoader;
    }

    public static void main() {
        launch();
    }

}