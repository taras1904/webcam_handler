package com.kovaliv.controller;

import com.kovaliv.threads.VideoThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MainController {

    @FXML
    private TextField pathText;
    @FXML
    private Button startButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button stopButton;
    @FXML
    private ImageView mainScreen;
    @FXML
    private ImageView secondScreen;
    @FXML
    private VideoThread videoThread;


    @FXML
    private void start(ActionEvent event) {
        startButton.setText("Started");
        stopButton.setText("Stop");
        videoThread = new VideoThread(mainScreen, secondScreen);
        videoThread.start();
    }

    @FXML
    private void stop(ActionEvent event) {
        startButton.setText("Start");
        stopButton.setText("Stopped");
        videoThread.cameraStop();
        videoThread.stop();
    }

    public void saveFiles(ActionEvent actionEvent) {
        saveButton.setText("Saved");
        videoThread.saveImg();
    }

    public void textChanged(ActionEvent actionEvent) {
        System.out.println(pathText.getText());
        videoThread.setPath(pathText.getText());
    }

}
