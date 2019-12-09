package com.kovaliv.controller;

import com.kovaliv.threads.VideoThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.concurrent.Exchanger;

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
    private Label determinationResult;

    private VideoThread videoThread;

    private Exchanger<String> det;


    @FXML
    private void start(ActionEvent event) {
        startButton.setText("Started");
        stopButton.setText("Stop");
        det = new Exchanger<String>();
        videoThread = new VideoThread(mainScreen, secondScreen);
        videoThread.setDet(det);
        videoThread.start();/*
        String message;
        try {
            message = det.exchange(null);
            determinationResult.setText(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
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
        videoThread.setPath(pathText.getText());
    }

}
