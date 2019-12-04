package com.kovaliv.imageHandlers;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveImage {
    private static int count = 0;
    private static int count2 = 0;
    private BufferedImage image;
    private String path;
    private Dimension dimension;

    public SaveImage(BufferedImage bufferedImage) {
        image = bufferedImage;
        dimension = new Dimension(40, 60);
        path = "C:\\Users\\user\\Desktop\\mzkit";
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void saveBmp() {
        count++;
        try {
            ImageIO.write(scale(), "BMP", new File(path + "\\img" + count + ".bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage scale() {
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

    public void saveTxt() {
        BufferedImage scaledImg = scale();
        String str = "";
        BufferedWriter bufferedWriter = null;
        count2++;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(path + "\\txt" + count + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < dimension.height; i++) {
            for (int j = 0; j < dimension.width; j++) {
                if (scaledImg.getRGB(j, i) == Color.BLACK.getRGB()) {
                    str += "1 ";
                } else {
                    str += "0 ";
                }
            }
            str += "\n";

        }
        try {
            bufferedWriter.write(str);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
