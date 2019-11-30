package com.kovaliv.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button startButton;

    @FXML
    private Button saveButton;

    @FXML
    private void start(ActionEvent event) {
        startButton.setText("Started");
    }


    public void saveFiles(ActionEvent actionEvent) {
        saveButton.setText("Saved");
    }
}
