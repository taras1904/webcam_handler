package com.kovaliv.imageHandlers.Filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImpulsFilter implements Filter {
    BufferedImage image;
    private int[][] colorMas;

    @Override
    public BufferedImage filter(BufferedImage bufferedImage) {
        image = bufferedImage;
        colorMas = new int[image.getWidth()][image.getHeight()];
        getBiteCode();
        finding();
        int num = countNumOfPoints(2);
        int i = 2;
        while (num > 1) {
            if (num < 30) {
                clearPoint(i);
            }
            num = countNumOfPoints(++i);
        }
        return transformToBufferedImage();
    }

    private void mark(int x, int y, int num) {
        colorMas[x][y] = num;
        if (x < 1 || y < 1 || x > image.getWidth() - 2 || y > image.getHeight() - 2) {
            return;
        }
        if (colorMas[x - 1][y] == 1) {
            mark(x - 1, y, num);
        }
        if (colorMas[x + 1][y] == 1) {
            mark(x + 1, y, num);
        }
        if (colorMas[x][y + 1] == 1) {
            mark(x, y + 1, num);
        }
        if (colorMas[x][y - 1] == 1) {
            mark(x, y - 1, num);
        }

    }

    private void finding() {
        int num = 2;
        while (isOne()) {
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    if (colorMas[i][j] == 1) {
                        mark(i, j, num);
                        num++;
                    }
                }
            }

        }
        return;
    }

    private boolean isOne() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (colorMas[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void getBiteCode() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (image.getRGB(i, j) == Color.BLACK.getRGB()) {
                    colorMas[i][j] = 1;
                } else {
                    colorMas[i][j] = 0;
                }
            }
        }
    }

    private BufferedImage transformToBufferedImage() {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                newImage.setRGB(i, j, chooseColor(colorMas[i][j]).getRGB());
            }
        }
        return newImage;
    }

    private Color chooseColor(int n) {
        if (n == 0) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    private int countNumOfPoints(int n) {
        int count = 0;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (colorMas[i][j] == n) {
                    count++;
                }
            }
        }
        return count;
    }

    private void clearPoint(int n) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (colorMas[i][j] == n) {
                    colorMas[i][j] = 0;
                }
            }
        }
    }

}
