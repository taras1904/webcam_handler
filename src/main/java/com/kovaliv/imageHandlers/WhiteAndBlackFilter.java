package com.kovaliv.imageHandlers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WhiteAndBlackFilter {
    BufferedImage image;
    Dimension dimension;

    public WhiteAndBlackFilter(Dimension dimension) {
        this.dimension = dimension;
    }

    public BufferedImage filter(BufferedImage bufferedImage) {
        image = bufferedImage;
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                Pixel pixel = new Pixel(image.getRGB(i, j));
                if (isBlack(pixel)) {
                    image.setRGB(i, j, Color.BLACK.getRGB());
                } else {
                    image.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
        }
        return image;
    }

    private boolean isBlack(Pixel pixel) {
        return pixel.getSum() < 300;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
