package com.kovaliv.threads;

import com.github.sarxos.webcam.Webcam;
import com.kovaliv.imageHandlers.Filters.BorderFilter;
import com.kovaliv.imageHandlers.Filters.ImpulsFilter;
import com.kovaliv.imageHandlers.Filters.MarkFilter;
import com.kovaliv.imageHandlers.Filters.WhiteAndBlackFilter;
import com.kovaliv.imageHandlers.ImageEditor;
import com.kovaliv.imageHandlers.SaveImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class VideoThread extends Thread {
    ImageView mainScreen;
    ImageView secondScreen;
    Webcam webcam;
    Dimension dimension;
    String path;
    boolean save;
    boolean cameraChange;

    public VideoThread(ImageView mainScreen, ImageView secondScreen, Webcam webcam) {
        this.mainScreen = mainScreen;
        this.secondScreen = secondScreen;
        dimension = new Dimension(176, 144); // 176 * 144, 320 * 240
        this.webcam = webcam;
        if (webcam.isOpen()) {
            webcam.close();
        }
        webcam.setViewSize(dimension);
        webcam.open();
        save = false;
        cameraChange = false;
        path = "C:\\Users\\user\\Desktop\\mzkit";
    }

    @Override
    public void run() {

        Image image;
        while (true) {
            if (cameraChange) {
                continue;
            }
            BufferedImage bufferedImage = webcam.getImage();
            BufferedImage copy = ImageEditor.copyImage(bufferedImage);

            bufferedImage = filters(bufferedImage);
            image = SwingFXUtils.toFXImage(bufferedImage, null);

            if (save) {
                saveImages(bufferedImage);
                save = false;
            }
            secondScreen.setImage(image);

            image = SwingFXUtils.toFXImage(ImageEditor.copyRed(copy, bufferedImage), null);
            mainScreen.setImage(image);

        }
    }

    private void saveImages(BufferedImage bufferedImage) {
        SaveImage saveImage = new SaveImage();
        saveImage.setPath(path);
        List<BufferedImage> bufferedImages = ImageEditor.getObjects(bufferedImage);
        for (BufferedImage image : bufferedImages) {
            saveImage.saveBmp(image);
            saveImage.saveTxt(image);
        }
    }

    public BufferedImage filters(BufferedImage bufferedImage) {
        WhiteAndBlackFilter whiteAndBlackFilter = new WhiteAndBlackFilter();
        BorderFilter borderFilter = new BorderFilter();
        ImpulsFilter impulsFilter = new ImpulsFilter();
        MarkFilter markFilter = new MarkFilter();

        bufferedImage = whiteAndBlackFilter.filter(bufferedImage);
        bufferedImage = borderFilter.filter(bufferedImage);
        bufferedImage = impulsFilter.filter(bufferedImage);
        bufferedImage = markFilter.filter(bufferedImage);

        return bufferedImage;
    }

    public void setWebcam(Webcam webcam) {
        cameraChange = true;
        this.webcam.close();
        this.webcam = webcam;
        webcam.setViewSize(dimension);
        webcam.open();
        cameraChange = false;
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
