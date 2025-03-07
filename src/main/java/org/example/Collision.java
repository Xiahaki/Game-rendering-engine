package org.example;


public class Collision {
    public double vectorSize;
    public int color;

    public Collision(final double vectorSize, final int color) {
        this.vectorSize = vectorSize;
        this.color = color;
    }

    public double getVectorSize() {
        return vectorSize;
    }

    public int getColor() {
        return color;
    }
}
