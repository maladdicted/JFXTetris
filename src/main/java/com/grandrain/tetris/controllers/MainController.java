package com.grandrain.tetris.controllers;

import com.grandrain.tetris.App;
import com.grandrain.tetris.Audio;
import com.grandrain.tetris.gui.GameOverPane;
import com.grandrain.tetris.logic.DownData;
import com.grandrain.tetris.logic.ViewData;
import com.grandrain.tetris.logic.events.EventSource;
import com.grandrain.tetris.logic.events.InputEventListener;
import com.grandrain.tetris.logic.events.MoveEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final int BRICK_SIZE = 20;

    @FXML
    private GridPane gamePane;

    @FXML
    private Text scoreValue;

    @FXML
    private GridPane brickPane;

    @FXML
    private ToggleButton pauseButton;

    @FXML
    private GameOverPane gameOverPane;

    private Rectangle[][] displayMatrix;

    private InputEventListener eventListener;

    private Rectangle[][] rectangles;

    private Timeline timeLine;

    private final BooleanProperty isPause = new SimpleBooleanProperty();

    private final BooleanProperty isGameOver = new SimpleBooleanProperty();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Audio.playMusic();
        gamePane.setFocusTraversable(true);
        gamePane.requestFocus();
        gamePane.setOnKeyPressed(keyEvent -> {
            if (isPause.getValue() == Boolean.FALSE && isGameOver.getValue() == Boolean.FALSE) {
                if (keyEvent.getCode() == KeyCode.A) {
                    refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventSource.USER)));
                    keyEvent.consume();
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    refreshBrick(eventListener.onRightEvent(new MoveEvent(EventSource.USER)));
                    keyEvent.consume();
                }
                if (keyEvent.getCode() == KeyCode.W) {
                    refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventSource.USER)));
                    keyEvent.consume();
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    moveDown(new MoveEvent(EventSource.USER));
                    keyEvent.consume();
                }
            }
            if (keyEvent.getCode() == KeyCode.N) {
                newGame();
            }
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                pauseButton.selectedProperty().setValue(!pauseButton.selectedProperty().getValue());
            }

        });
        gameOverPane.setVisible(false);
        pauseButton.selectedProperty().bindBidirectional(isPause);
        pauseButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                timeLine.pause();
                pauseButton.setText("Відновити");
            } else {
                timeLine.play();
                pauseButton.setText("Пауза");
            }
        });
        final Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);
        scoreValue.setEffect(reflection);
    }

    public void initGameView(int[][] boardMatrix, ViewData brick) {
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePane.add(rectangle, j, i - 2);
            }
        }

        rectangles = new Rectangle[brick.getBrickData().length][brick.getBrickData()[0].length];
        for (int i = 0; i < brick.getBrickData().length; i++) {
            for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(getFillColor(brick.getBrickData()[i][j]));
                rectangles[i][j] = rectangle;
                brickPane.add(rectangle, j, i);
            }
        }
        brickPane.setLayoutX(gamePane.getLayoutX() + brick.getxPosition() * brickPane.getVgap()
                + brick.getxPosition() * BRICK_SIZE);
        brickPane.setLayoutY(-42 + gamePane.getLayoutY() + brick.getyPosition() * brickPane.getHgap()
                + brick.getyPosition() * BRICK_SIZE);

        timeLine = new Timeline(new KeyFrame(
                Duration.millis(400),
                ae -> moveDown(new MoveEvent(EventSource.THREAD))
        ));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    private Paint getFillColor(int i) {
        return switch (i) {
            case 0 -> Color.TRANSPARENT;
            case 1 -> Color.TEAL;
            case 2 -> Color.ORANGE;
            case 3 -> Color.DARKGREEN;
            case 4 -> Color.BLUEVIOLET;
            case 5 -> Color.BROWN;
            case 6 -> Color.DARKBLUE;
            case 7 -> Color.RED;
            default -> Color.BLACK;
        };
    }

    private void refreshBrick(ViewData brick) {
        if (isPause.getValue() == Boolean.FALSE) {
            brickPane.setLayoutX(gamePane.getLayoutX() + brick.getxPosition() * brickPane.getVgap()
                    + brick.getxPosition() * BRICK_SIZE);
            brickPane.setLayoutY(-42 + gamePane.getLayoutY() + brick.getyPosition() * brickPane.getHgap()
                    + brick.getyPosition() * BRICK_SIZE);
            for (int i = 0; i < brick.getBrickData().length; i++) {
                for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                    setRectangleData(brick.getBrickData()[i][j], rectangles[i][j]);
                }
            }
        }
    }

    public void refreshGameBackground(int[][] board) {
        for (int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(getFillColor(color));
    }

    private void moveDown(MoveEvent event) {
        if (isPause.getValue() == Boolean.FALSE) {
            DownData downData = eventListener.onDownEvent(event);
            refreshBrick(downData.getViewData());
        }
        gamePane.requestFocus();
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void bindScore(IntegerProperty integerProperty) {
        scoreValue.textProperty().bind(integerProperty.asString());
    }

    public void gameOver() {
        timeLine.stop();
        gameOverPane.setVisible(true);
        isGameOver.setValue(Boolean.TRUE);
        Audio.playGameOver();
    }

    @FXML
    public void newGame() {
        Audio.stopMusic();
        timeLine.stop();
        gameOverPane.setVisible(false);
        eventListener.createNewGame();
        gamePane.requestFocus();
        timeLine.play();
        isPause.setValue(Boolean.FALSE);
        isGameOver.setValue(Boolean.FALSE);
        Audio.playMusic();
    }

    @FXML
    public void pauseGame() {
        gamePane.requestFocus();
    }

    @FXML
    public void showSettingView() throws IOException {
        pauseButton.setSelected(true);
        App.load("settings", "Налаштування", new Stage());
        gamePane.requestFocus();
    }

    @FXML
    private void showInfo() throws Exception {
        pauseButton.setSelected(true);
        Alert alert = new Alert(AlertType.INFORMATION, null, ButtonType.YES, ButtonType.NO);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Інформація");
        alert.setHeaderText("""
                Версія 1.1.0-BETA
                https://github.com/Ieeht/JFXTetris
                Перейти до репозиторію?""");
        alert.setContentText("""
                Над проєктом працювали студенти групи ІТ-41:
                Вечер Максим — програміст
                Гандзюк Володимир — тестувальник
                Новосад Вероніка — дизайнер
                Паламарчук Ярослав — кодер
                Турчиняк Денис — звукорежисер""");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Desktop.getDesktop().browse(new URI("https://github.com/Ieeht/JFXTetris"));
        }
        gamePane.requestFocus();
    }

}
