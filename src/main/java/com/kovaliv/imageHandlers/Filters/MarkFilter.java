package com.kovaliv.imageHandlers.Filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MarkFilter implements Filter {
    private BufferedImage image;
    private int[][] colorMas;

    @Override
    public BufferedImage filter(BufferedImage bufferedImage) {
        colorMas = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        image = bufferedImage;
        getBiteCode();
        finding();
        markBorders();
        return transformToBufferedImage();
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

    private void markBorders() {
        int num = countNumOfPoints(2);
        int i = 2;
        while (num > 1) {
            border(i);
            num = countNumOfPoints(++i);
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

    private Color chooseColor(int i) {
        if (i == 0) {
            return Color.WHITE;
        }
        if (i == -1) {
            return Color.RED;
        }
        return Color.BLACK;
    }

    private void border(int n) {
        int xTop = xTop(n) - 1;
        int xBottom = xBottom(n) + 1;
        int yLeft = yLeft(n) - 1;
        int yRight = yRight(n) + 1;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if ((i == xTop || i == xBottom) && j <= yRight && j >= yLeft) {
                    colorMas[i][j] = -1;
                }
                if ((j == yLeft || j == yRight) && i >= xTop && i <= xBottom) {
                    colorMas[i][j] = -1;
                }
            }
        }
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

    private int xTop(int n) {
        int x = image.getWidth();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (colorMas[i][j] == n && i < x) {
                    x = i;
                }
            }
        }
        return x;
    }

    private int xBottom(int n) {
        int x = 0;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (colorMas[i][j] == n && i > x) {
                    x = i;
                }
            }
        }
        return x;
    }

    private int yLeft(int n) {
        int y = image.getHeight();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (colorMas[i][j] == n && j < y) {
                    y = j;
                }
            }
        }
        return y;
    }

    private int yRight(int n) {
        int y = 0;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (colorMas[i][j] == n && j > y) {
                    y = j;
                }
            }
        }
        return y;
    }
}
