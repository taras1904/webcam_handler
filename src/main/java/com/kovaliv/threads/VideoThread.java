package com.kovaliv.threads;

import com.github.sarxos.webcam.Webcam;
import com.kovaliv.imageHandlers.FindElements;
import com.kovaliv.imageHandlers.SaveImage;
import com.kovaliv.imageHandlers.WhiteAndBlackFilter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Exchanger;

public class VideoThread extends Thread {
    ImageView mainScreen;
    ImageView secondScreen;
    Webcam webcam;
    Dimension dimension;
    WhiteAndBlackFilter whiteAndBlackFilter;
    FindElements findElements;
    Label determinationResult;
    String path;
    boolean save;
    Exchanger<String> det;

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

    public void setDet(Exchanger<String> det) {
        this.det = det;
    }

    @Override
    public void run() {

        while (true) {
            BufferedImage bufferedImage = webcam.getImage();
            Image image;
            BufferedImage buf = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < bufferedImage.getWidth(); i++) {
                for (int j = 0; j < bufferedImage.getHeight(); j++) {
                    buf.setRGB(i, j, bufferedImage.getRGB(i, j));
                }
            }
            findElements.setStartImage(buf);
            try {
                bufferedImage = findElements.find(whiteAndBlackFilter.filter(bufferedImage));
                image = SwingFXUtils.toFXImage(bufferedImage, null);
                String buffer = findElements.getDetectedNumbers();
                if (buffer.length() > 2) {
                    System.out.println(buffer);
                }
                if (save) {
                    findElements.fillList();
                    for (BufferedImage bufferedImage1 : findElements.getBufferedImageList()) {
                        saveImage(bufferedImage1);
                    }
                    save = false;
                }
                secondScreen.setImage(image);
                image = SwingFXUtils.toFXImage(findElements.getStartImage(), null);
                mainScreen.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void saveImage(BufferedImage bufferedImage) {
        SaveImage saveImage = new SaveImage(bufferedImage);
        saveImage.setPath(path);
        saveImage.saveBmp();
        saveImage.saveTxt();
    }

    public void saveImg() {
        save = true;
    }

    public void setDeterminationResult(Label determinationResult) {
        this.determinationResult = determinationResult;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void cameraStop() {
        webcam.close();
    }
}
