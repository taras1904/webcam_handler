package com.kovaliv.threads;

import com.github.sarxos.webcam.Webcam;
import com.kovaliv.imageHandlers.FindElements;
import com.kovaliv.imageHandlers.SaveImage;
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
    String path;
    boolean save;

    public VideoThread(ImageView mainScreen, ImageView secondScreen) {
        this.mainScreen = mainScreen;
        this.secondScreen = secondScreen;
        dimension = new Dimension(320, 240); // 320 * 240
        whiteAndBlackFilter = new WhiteAndBlackFilter(dimension);
        webcam = Webcam.getDefault();
        webcam.setViewSize(dimension);
        webcam.open();
        findElements = new FindElements(dimension);
        save = false;
        path = "C:\\Users\\user\\Desktop\\mzkit";
    }

    @Override
    public void run() {

        while (true) {
            BufferedImage bufferedImage = webcam.getImage();
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            mainScreen.setImage(image);
            bufferedImage = findElements.find(whiteAndBlackFilter.filter(bufferedImage));
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            if (save) {
                findElements.fillList();
                for (BufferedImage bufferedImage1 : findElements.getBufferedImageList()) {
                    SaveImage saveImage = new SaveImage(bufferedImage1);
                    saveImage.setPath(path);
                    saveImage.saveBmp();
                    saveImage.saveTxt();
                }
                save = false;
            }
            secondScreen.setImage(image);
        }
    }

    public void saveImg() {
        save = true;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void cameraStop() {
        webcam.close();
    }
}
