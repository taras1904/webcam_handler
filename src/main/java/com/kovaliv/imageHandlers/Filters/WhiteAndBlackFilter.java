package com.kovaliv.imageHandlers.Filters;

import com.kovaliv.imageHandlers.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WhiteAndBlackFilter implements Filter {


    public BufferedImage filter(BufferedImage bufferedImage) {
        BufferedImage image = bufferedImage;
        Dimension dimension = new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
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

}
