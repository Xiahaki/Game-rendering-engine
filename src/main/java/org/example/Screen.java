package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen {
    private int width;
    private int height;
    private int distance;
    private double k;
    private Scene scene;

    public Screen(final int width, final int height, final int distance, final double k, final Scene scene) {
        this.width = width;
        this.height = height;
        this.distance = distance;
        this.k = k;
        this.scene = scene;
    }

    public BufferedImage getImage(Point3D corner, Point3D vectorX, Point3D vectorYBase, BufferedImage img) {
        var vectorY = vectorYBase.multiply(MathUtils.getLength(vectorX, Point3D.zero()) / MathUtils.getLength(vectorYBase, Point3D.zero()));
        var center = corner.plus(vectorX.multiply(width / 2)).plus(vectorY.multiply(height / 2));
        var pp = MathUtils.getPerpendicularPoint(vectorX, vectorY);

        var vectorZ = pp.multiply(distance / MathUtils.getLength(new Point3D(0, 0, 0), pp));

        for (int i = -1 * width / 2; i < width / 2; i++) {
            for (int j = -1 * height / 2; j < height / 2; j++) {
                var current = center.plus(vectorX.multiply(i))
                        .plus(vectorY.multiply(j));
                var ortagonal = center.plus(vectorZ)
                        .plus(vectorX.multiply(i * k))
                        .plus(vectorY.multiply(j * k));
                var coll = scene.traceRay(current, ortagonal);
                img.setRGB(i + width / 2, j + height / 2, coll.getRGB());
            }
        }
        return img;
    }
}
