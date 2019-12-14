package com.kovaliv.controller;

import com.github.sarxos.webcam.Webcam;
import com.kovaliv.threads.VideoThread;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.LinkedList;
import java.util.List;

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
    private ComboBox<String> cameras;

    @FXML
    private Label determinationResult;

    private VideoThread videoThread;
    private List<Webcam> webcams;
    private List<String> list;

    @FXML
    private void start(ActionEvent event) {
        startButton.setText("Started");
        stopButton.setText("Stop");
        videoThread = new VideoThread(mainScreen, secondScreen, getWebcam());
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

    private Webcam getWebcam() {
        webcams = Webcam.getWebcams();
        list = new LinkedList<>();
        for (Webcam webcam : webcams) {
            list.add(webcam.getName());
        }
        ObservableList<String> observableList = FXCollections.observableList(list);
        cameras.setItems(observableList);
        return webcams.get(0);
    }

    public void textChanged(ActionEvent actionEvent) {
        videoThread.setPath(pathText.getText());
    }

    public void changeWebcam(ActionEvent actionEvent) {
        videoThread.setWebcam(webcams.get(list.indexOf(cameras.getValue())));
    }
}
