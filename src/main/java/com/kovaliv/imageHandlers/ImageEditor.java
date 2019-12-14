package com.kovaliv.imageHandlers;

import com.kovaliv.imageHandlers.Filters.ScaleFilter;

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
        ScaleFilter scaleFilter = new ScaleFilter();
        for (int i = 0; i < image.getWidth() - 5; i++) {
            for (int j = 0; j < image.getHeight() - 5; j++) {
                if (image.getRGB(i, j) == Color.RED.getRGB() && image.getRGB(i, j + 1) == Color.RED.getRGB() && image.getRGB(i + 1, j) == Color.RED.getRGB()) {
                    i++;
                    j++;
                    list.add(scaleFilter.filter(image.getSubimage(i, j, getWidth(image, i, j), getHeight(image, i, j))));
                }
            }
        }
        return list;
    }

    private static int getHeight(BufferedImage image, int x, int y) {
        int i;
        for (i = 0; image.getRGB(x, y) != Color.RED.getRGB(); i++) {
            y++;
        }
        return i;
    }

    private static int getWidth(BufferedImage image, int x, int y) {
        int i;
        for (i = 0; image.getRGB(x, y) != Color.RED.getRGB(); i++) {
            x++;
        }
        return i;
    }

}
