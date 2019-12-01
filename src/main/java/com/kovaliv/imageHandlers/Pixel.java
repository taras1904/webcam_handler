package com.kovaliv.imageHandlers;

public class Pixel {

    private final int blue;
    private final int red;
    private final int green;

    public Pixel(int typeIntARGB) {
        this.red = typeIntARGB >> 16 & 0xFF;
        this.green = typeIntARGB >> 8 & 0xFF;
        this.blue = typeIntARGB & 0xFF;
    }

    public double getSum() {
        double ret = 0.0;
        ret += red;
        ret += green;
        ret += blue;
        return ret;
    }

}
