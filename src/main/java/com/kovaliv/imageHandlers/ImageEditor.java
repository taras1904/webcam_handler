package com.kovaliv.imageHandlers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageEditor {
    public static BufferedImage copyImage(BufferedImage bufferedImage) {
        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                newImage.setRGB(i, j, bufferedImage.getRGB(i, j));
            }
        }
        return newImage;
    }

    public static BufferedImage copyRed(BufferedImage toImage, BufferedImage fromImage) {
        for (int i = 0; i < toImage.getWidth(); i++) {
            for (int j = 0; j < toImage.getHeight(); j++) {
                if (fromImage.getRGB(i, j) == Color.RED.getRGB()) {
                    toImage.setRGB(i, j, Color.RED.getRGB());
                }
            }
        }
        return toImage;
    }

    public static List<BufferedImage> getObjects(BufferedImage image) {
        List<BufferedImage> list = new ArrayList<>();


        return list;
    }

}
