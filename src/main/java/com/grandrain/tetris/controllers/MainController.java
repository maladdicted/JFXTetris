package com.grandrain.tetris.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.awt.Desktop;
import java.net.URI;

public class MainController {

    @FXML
    private void infoButtonClick() throws Exception {
        Alert alert = new Alert(AlertType.INFORMATION, null, ButtonType.YES, ButtonType.NO);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Інформація");
        alert.setHeaderText("""
                Версія 1.0.0-ALPHA
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
    }
}
