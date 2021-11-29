package com.grandrain.tetris.controllers;

import com.grandrain.tetris.Audio;
import com.grandrain.tetris.data.Config;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private Slider effectsVolume;

    @FXML
    private Slider musicVolume;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        effectsVolume.setValue(Config.getEffectsVolume());
        musicVolume.setValue(Config.getMusicVolume());
    }

    @FXML
    public void setEffectsVolume(ObservableValue<Double> obs, double oldValue, double newValue) {
        Audio.setEffectsVolume(newValue);
    }

    @FXML
    public void setMusicVolume(ObservableValue<Double> obs, double oldValue, double newValue) {
        Audio.setMusicVolume(newValue);
    }

    @FXML
    private void showInfo() throws Exception {
        ButtonType yesButton = new ButtonType("Так");
        ButtonType noButton = new ButtonType("Ні");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, null, yesButton, noButton);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Інформація");
        alert.setHeaderText("""
                Версія 1.2.0
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

        if (alert.getResult() == yesButton) {
            Desktop.getDesktop().browse(new URI("https://github.com/Ieeht/JFXTetris"));
        }
    }

    @FXML
    public void applyConfig(ActionEvent e) throws IOException {
        Config.write(effectsVolume.getValue(), musicVolume.getValue());
        close(e);
    }

    @FXML
    public void close(ActionEvent e) {
        Audio.setEffectsVolume(Config.getEffectsVolume());
        Audio.setMusicVolume(Config.getMusicVolume());
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
