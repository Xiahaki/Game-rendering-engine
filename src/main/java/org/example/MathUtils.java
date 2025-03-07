package org.example;

public class MathUtils {
    private MathUtils() {
    }

    public static double getLength(Point3D x, Point3D y) {
        return Math.sqrt(Math.pow((x.getX() - y.getX()), 2) +
                Math.pow((x.getY() - y.getY()), 2) +
                Math.pow((x.getZ() - y.getZ()), 2));
    }

    public static Point3D getPerpendicularPoint(Point3D a, Point3D b) {
        return new Point3D(a.getY() * b.getZ() - a.getZ() * b.getY(), a.getZ() * b.getX() - a.getX() * b.getZ(),
                a.getX() * b.getY() - a.getY() * b.getX());
    }


    public static double coerceIn(double val, double min, double max) {
        if (val > max) {
            return max;
        } else if (val < min) {
            return min;
        }
        return val;
    }
}
