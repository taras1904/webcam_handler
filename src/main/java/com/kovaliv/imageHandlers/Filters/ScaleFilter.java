package com.kovaliv.imageHandlers.Filters;

import org.imgscalr.Scalr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScaleFilter implements Filter {
    @Override
    public BufferedImage filter(BufferedImage image) {
        Dimension dimension = new Dimension(40, 60);
        BufferedImage tempImage = Scalr.resize(image, dimension.width, dimension.height);
        int rizn;
        int top;
        BufferedImage imageNew = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        if (tempImage.getWidth() == dimension.width) {
            rizn = dimension.height - tempImage.getHeight();
            top = rizn / 2;
            for (int i = 0; i < dimension.width; i++) {
                for (int j = 0; j < dimension.height; j++) {
                    if (j < top || j >= dimension.height - rizn + top) {
                        imageNew.setRGB(i, j, Color.WHITE.getRGB());
                    } else {
                        imageNew.setRGB(i, j, tempImage.getRGB(i, j - top));
                    }
                }
            }
        } else {
            rizn = dimension.width - tempImage.getWidth();
            top = rizn / 2;
            for (int i = 0; i < dimension.width; i++) {
                for (int j = 0; j < dimension.height; j++) {
                    if (i < top || i > dimension.width - rizn + top - 1) {
                        imageNew.setRGB(i, j, Color.WHITE.getRGB());
                    } else {
                        imageNew.setRGB(i, j, tempImage.getRGB(i - top, j));
                    }
                }
            }
        }
        return imageNew;
    }
}
