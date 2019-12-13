package com.kovaliv.imageHandlers.Filters;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage filter(BufferedImage bufferedImage);
}
