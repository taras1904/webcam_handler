package com.kovaliv.threads;

import com.github.sarxos.webcam.Webcam;
import com.kovaliv.imageHandlers.FindElements;
import com.kovaliv.imageHandlers.WhiteAndBlackFilter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VideoThread extends Thread {
    ImageView mainScreen;
    ImageView secondScreen;
    Webcam webcam;
    Dimension dimension;
    WhiteAndBlackFilter whiteAndBlackFilter;
    FindElements findElements;

    public VideoThread(ImageView mainScreen, ImageView secondScreen) {
        this.mainScreen = mainScreen;
        this.secondScreen = secondScreen;
        dimension = new Dimension(176, 144); // 320 * 240
        whiteAndBlackFilter = new WhiteAndBlackFilter(dimension);
        webcam = Webcam.getDefault();
        webcam.setViewSize(dimension);
        webcam.open();
        findElements = new FindElements(dimension);
    }

    @Override
    public void run() {

        while (true) {
            BufferedImage bufferedImage = webcam.getImage();
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            mainScreen.setImage(image);
            image = SwingFXUtils.toFXImage(findElements.find(whiteAndBlackFilter.filter(bufferedImage)), null);
            secondScreen.setImage(image);
        }
    }

    public void cameraStop() {
        webcam.close();
    }
}
