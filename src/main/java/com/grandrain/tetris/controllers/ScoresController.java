package com.grandrain.tetris.controllers;

import com.grandrain.tetris.data.Scores;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoresController implements Initializable {

    @FXML
    private TextArea textArea;

    @FXML
    public void initialize(URL location, ResourceBundle resources)  {
        try {
            textArea.setText(Scores.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clear() throws IOException {
        ButtonType yesButton = new ButtonType("Так");
        ButtonType noButton = new ButtonType("Ні");

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION, null, yesButton, noButton
        );

        alert.setHeaderText("Ви дійсно бажаєте скинути результати?");
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Підтвердіть дію");
        alert.showAndWait();

        if (alert.getResult() == yesButton) {
            Scores.clear();
            textArea.setText(Scores.read());
        }
    }

    @FXML
    public void copy() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(textArea.getText());
        clipboard.setContent(content);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Результати скопійовані в буфер обміну");
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    @FXML
    public void close(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
