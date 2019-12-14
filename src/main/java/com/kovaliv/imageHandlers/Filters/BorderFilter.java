package com.kovaliv.imageHandlers.Filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BorderFilter implements Filter {

    private BufferedImage bufferedImage;

    @Override
    public BufferedImage filter(BufferedImage image) {
        bufferedImage = image;
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            if (bufferedImage.getRGB(i, 0) == Color.BLACK.getRGB()) {
                clear(i, 0);
            }
            if (bufferedImage.getRGB(i, bufferedImage.getHeight() - 1) == Color.BLACK.getRGB()) {
                clear(i, bufferedImage.getHeight() - 1);
            }
        }
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            if (bufferedImage.getRGB(0, i) == Color.BLACK.getRGB()) {
                clear(0, i);
            }
            if (bufferedImage.getRGB(bufferedImage.getWidth() - 1, i) == Color.BLACK.getRGB()) {
                clear(bufferedImage.getWidth() - 1, i);
            }
        }
        return bufferedImage;
    }

    private void clear(int x, int y) {
        bufferedImage.setRGB(x, y, Color.WHITE.getRGB());
        if (y < bufferedImage.getHeight() - 2) {
            if (bufferedImage.getRGB(x, y + 1) == Color.BLACK.getRGB()) {
                clear(x, y + 1);
            }
        }
        if (y > 0) {
            if (bufferedImage.getRGB(x, y - 1) == Color.BLACK.getRGB()) {
                clear(x, y - 1);
            }
        }
        if (x > 0) {
            if (bufferedImage.getRGB(x - 1, y) == Color.BLACK.getRGB()) {
                clear(x - 1, y);
            }
        }
        if (x < bufferedImage.getWidth() - 2) {
            if (bufferedImage.getRGB(x + 1, y) == Color.BLACK.getRGB()) {
                clear(x + 1, y);
            }
        }
    }


}
