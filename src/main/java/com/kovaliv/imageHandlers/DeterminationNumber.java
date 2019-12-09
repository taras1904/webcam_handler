package com.kovaliv.imageHandlers;

import com.kovaliv.exceptions.DontValidNumberException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class DeterminationNumber {
    private static List<BufferedImage> numbers;
    private static double[] koef;

    public static int determinateNumber(BufferedImage image) throws DontValidNumberException {
        fillNumbers();
        koef = new double[10];
        int i = 0;
        for (BufferedImage f0 : numbers) {
            koef[i++] = equals(f0, image);
        }
        if (koef[minI()] < 0.2) {
            return minI();
        }
        throw new DontValidNumberException();
    }

    private static int minI() {
        double min = koef[0];
        int minI = 0;
        for (int i = 1; i < 10; i++) {
            if (koef[i] < min) {
                min = koef[i];
                minI = i;
            }
        }
        return minI;
    }

    private static void fillNumbers() {
        numbers = new ArrayList<>();
        try {
            numbers.add(ImageIO.read(new File("src/main/resources/img/0.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/1.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/2.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/3.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/4.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/5.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/6.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/7.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/8.bmp")));
            numbers.add(ImageIO.read(new File("src/main/resources/img/9.bmp")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double equals(BufferedImage f0, BufferedImage f) {
        int sum = 0;
        for (int i = 0; i < f0.getWidth(); i++) {
            for (int j = 0; j < f0.getWidth(); j++) {
                sum += isBlack(f.getRGB(i, j)) - isBlack(f0.getRGB(i, j));
            }
        }
        double ret = (double) sum / (f0.getWidth() * f0.getHeight());
        return ret > 0 ? ret : -ret;
    }

    private static int isBlack(int rgb) {
        if (rgb == Color.BLACK.getRGB()) {
            return 1;
        }
        return 0;
    }

}
