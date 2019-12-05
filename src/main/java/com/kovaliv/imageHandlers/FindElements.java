package com.kovaliv.imageHandlers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FindElements {
    private Dimension dimension;
    private BufferedImage image;
    private BufferedImage startImage;
    private int[][] colorMas;
    private List<BufferedImage> bufferedImageList;


    public FindElements(Dimension dimension) {
        this.dimension = dimension;
        colorMas = new int[dimension.width][dimension.height];
        bufferedImageList = new ArrayList<BufferedImage>();
    }

    public BufferedImage getStartImage() {
        return startImage;
    }

    public void setStartImage(BufferedImage startImage) {
        this.startImage = startImage;
    }

    public BufferedImage find(BufferedImage bufferedImage) {
        image = bufferedImage;
        getBiteCode();
        clearBorder();
        finding();
        ImpulsFilter impulsFilter = new ImpulsFilter();
        image = impulsFilter.filter(colorMas, dimension);
        getBiteCode();
        finding();
        markBorders();
        return transformToBufferedImage();
    }

    private BufferedImage transformToBufferedImage() {
        BufferedImage image = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                image.setRGB(i, j, chooseColor(colorMas[i][j]).getRGB());
                if (colorMas[i][j] == -1) {
                    startImage.setRGB(i, j, Color.RED.getRGB());
                }
            }
        }
        return image;
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

    private void markBorders() {
        int num = countNumOfPoints(2);
        int i = 2;
        while (num > 1) {
            border(i);
            num = countNumOfPoints(++i);
        }
    }

    private void border(int n) {
        int xTop = xTop(n) - 1;
        int xBottom = xBottom(n) + 1;
        int yLeft = yLeft(n) - 1;
        int yRight = yRight(n) + 1;
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                if ((i == xTop || i == xBottom) && j <= yRight && j >= yLeft) {
                    colorMas[i][j] = -1;
                }
                if ((j == yLeft || j == yRight) && i >= xTop && i <= xBottom) {
                    colorMas[i][j] = -1;
                }
            }
        }
    }

    public void fillList() {
        int num = countNumOfPoints(2);
        int s = 2;
        while (num > 1) {
            int xTop = xTop(s) - 1;
            int xBottom = xBottom(s) + 1;
            int yLeft = yLeft(s) - 1;
            int yRight = yRight(s) + 1;
            bufferedImageList.add(image.getSubimage(xTop + 1, yLeft + 1, xBottom - xTop - 2, yRight - yLeft - 2));
            num = countNumOfPoints(++s);
        }
    }

    public List<BufferedImage> getBufferedImageList() {
        return bufferedImageList;
    }

    private int xTop(int n) {
        int x = dimension.width;
        for (int i = 1; i < dimension.width; i++) {
            for (int j = 1; j < dimension.height; j++) {
                if (colorMas[i][j] == n && i < x) {
                    x = i;
                }
            }
        }
        return x;
    }

    private int xBottom(int n) {
        int x = 0;
        for (int i = 1; i < dimension.width; i++) {
            for (int j = 1; j < dimension.height; j++) {
                if (colorMas[i][j] == n && i > x) {
                    x = i;
                }
            }
        }
        return x;
    }

    private int yLeft(int n) {
        int y = dimension.height;
        for (int i = 1; i < dimension.width; i++) {
            for (int j = 1; j < dimension.height; j++) {
                if (colorMas[i][j] == n && j < y) {
                    y = j;
                }
            }
        }
        return y;
    }

    private int yRight(int n) {
        int y = 0;
        for (int i = 1; i < dimension.width; i++) {
            for (int j = 1; j < dimension.height; j++) {
                if (colorMas[i][j] == n && j > y) {
                    y = j;
                }
            }
        }
        return y;
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

    private void finding() {
        int x = 0;
        int y = 0;
        int num = 2;
        while (isOne()) {
            for (int i = 1; i < dimension.width - 1; i++) {
                for (int j = 1; j < dimension.height - 1; j++) {
                    if (colorMas[i][j] == 1) {
                        x = i;
                        y = j;
                        mark(x, y, num);
                        num++;
                    }
                }
            }

        }
        return;
    }

    private void mark(int x, int y, int num) {
        colorMas[x][y] = num;
        if (x < 1 || y < 1 || x > dimension.width - 2 || y > dimension.height - 2) {
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
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                if (colorMas[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void getBiteCode() {
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                if (image.getRGB(i, j) == Color.BLACK.getRGB()) {
                    colorMas[i][j] = 1;
                } else {
                    colorMas[i][j] = 0;
                }
            }
        }
    }

    private void clearBorder() {
        for (int i = 0; i < dimension.width; i++) {
            colorMas[i][0] = 0;
            colorMas[i][1] = 0;
            colorMas[i][2] = 0;
            colorMas[i][3] = 0;
        }
        for (int i = 0; i < dimension.width; i++) {
            colorMas[i][dimension.height - 1] = 0;
            colorMas[i][dimension.height - 4] = 0;
            colorMas[i][dimension.height - 2] = 0;
            colorMas[i][dimension.height - 3] = 0;
        }
        for (int i = 0; i < dimension.height; i++) {
            colorMas[0][i] = 0;
            colorMas[1][i] = 0;
            colorMas[2][i] = 0;
            colorMas[3][i] = 0;
        }
        for (int i = 0; i < dimension.height; i++) {
            colorMas[dimension.width - 1][i] = 0;
            colorMas[dimension.width - 2][i] = 0;
            colorMas[dimension.width - 3][i] = 0;
            colorMas[dimension.width - 4][i] = 0;
        }
    }


}
