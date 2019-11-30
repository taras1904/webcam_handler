package com.kovaliv.imageHandlers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImpulsFilter {
    private Dimension dimension;
    private int[][] colorMas;

    public BufferedImage filter(int[][] colorMas, Dimension dimension) {
        this.dimension = dimension;
        this.colorMas = colorMas;
        int num = countNumOfPoints(2);
        int i = 2;
        while (num > 1) {
            if (num < 15) {
                clearPoint(i);
            }
            num = countNumOfPoints(++i);
        }
        return transformToBufferedImage();
    }

    private BufferedImage transformToBufferedImage() {
        BufferedImage image = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                image.setRGB(i, j, chooseColor(colorMas[i][j]).getRGB());
            }
        }
        return image;
    }

    private Color chooseColor(int n) {
        if (n == 0) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    private int countNumOfPoints(int n) {
        int count = 0;
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                if (colorMas[i][j] == n) {
                    count++;
                }
            }
        }
        return count;
    }

    private void clearPoint(int n) {
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                if (colorMas[i][j] == n) {
                    colorMas[i][j] = 0;
                }
            }
        }
    }
}
