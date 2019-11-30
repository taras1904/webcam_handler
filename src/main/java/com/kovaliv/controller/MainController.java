package com.kovaliv.controller;

import com.kovaliv.threads.VideoThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MainController {

    @FXML
    private Button startButton;
    @FXML
    private Button saveButton;
    @FXML
    private ImageView mainScreen;
    @FXML
    private ImageView secondScreen;

    @FXML
    private void start(ActionEvent event) {
        startButton.setText("Started");
        VideoThread videoThread = new VideoThread(mainScreen, secondScreen);
        videoThread.start();
    }


    public void saveFiles(ActionEvent actionEvent) {
        saveButton.setText("Saved");
    }
}
