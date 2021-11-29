package com.grandrain.tetris.controllers;

import com.grandrain.tetris.Audio;
import com.grandrain.tetris.Data;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private Slider effectsVolume;

    @FXML
    private Slider musicVolume;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        effectsVolume.setValue(Data.getEffectsVolume());
        musicVolume.setValue(Data.getMusicVolume());
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
    public void applyConfig() throws IOException {
        Data.applyConfig(effectsVolume.getValue(), musicVolume.getValue());
    }

    @FXML
    public void close(ActionEvent e) {
        Audio.setEffectsVolume(Data.getEffectsVolume());
        Audio.setMusicVolume(Data.getMusicVolume());
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
