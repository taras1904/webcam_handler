package com.kovaliv.imageHandlers;

import com.kovaliv.imageHandlers.Filters.ScaleFilter;

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
    private String path;
    private ScaleFilter scaleFilter;

    public SaveImage() {
        scaleFilter = new ScaleFilter();
        path = "C:\\Users\\user\\Desktop\\mzkit";
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void saveBmp(BufferedImage bufferedImage) {
        count++;
        try {
            ImageIO.write(scaleFilter.filter(bufferedImage), "BMP", new File(path + "\\img" + count + ".bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTxt(BufferedImage bufferedImage) {
        BufferedImage scaledImg = scaleFilter.filter(bufferedImage);
        StringBuilder str = new StringBuilder();
        BufferedWriter bufferedWriter = null;
        count2++;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(path + "\\txt" + count2 + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < scaledImg.getHeight(); i++) {
            for (int j = 0; j < scaledImg.getWidth(); j++) {
                if (scaledImg.getRGB(j, i) == Color.BLACK.getRGB()) {
                    str.append("1 ");
                } else {
                    str.append("0 ");
                }
            }
            str.append("\n");

        }
        try {
            bufferedWriter.write(str.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
