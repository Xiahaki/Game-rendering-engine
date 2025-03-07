package org.example;

import java.util.Optional;

public class Sphere implements GeometryUnit {
    private Point3D center;
    private int radius;
    private Texture texture;

    public Sphere(final Point3D x, final int radius, final Texture color) {
        this.center = x;
        this.radius = radius;
        this.texture = color;
    }

    @Override
    public Optional<Point3D> getCollision(Point3D x, Point3D y) {
        double k1 = 0.0 + x.getX() - y.getX();
        double k2 = 0.0 + x.getY() - y.getY();
        double k3 = 0.0 + x.getZ() - y.getZ();

        double b1 = 0.0 + y.getX();
        double b2 = 0.0 + y.getY();
        double b3 = 0.0 + y.getZ();

        double a = (k1 * k1 + k2 * k2 + k3 * k3);
        double b = 2 * (k1 * (b1 - center.getX()) + k2 * (b2 - center.getY()) + k3 * (b3 - center.getZ()));
        double c = Math.pow(b1 - center.getX(), 2)
                + Math.pow(b2 - center.getY(), 2)
                + Math.pow(b3 - center.getZ(), 2)
                - radius * radius;

        double d = b * b - 4 * a * c;
        if (d < 0) {
            return Optional.empty();
        }
        double t1 = (-b + Math.sqrt(d)) / (2 * a);
        double t2 = (-b - Math.sqrt(d)) / (2 * a);
        double baseLength = MathUtils.getLength(x, y);
        Point3D p1 = new Point3D((int) (k1 * t1 + b1), (int) (k2 * t1 + b2), (int) (k3 * t1 + b3));
        Point3D p2 = new Point3D((int) (k1 * t2 + b1), (int) (k2 * t2 + b2), (int) (k3 * t2 + b3));
        if (MathUtils.getLength(p1, x) > baseLength || MathUtils.getLength(p1, y) > baseLength) {
            p1 = null;
        }
        if (MathUtils.getLength(p2, x) > baseLength || MathUtils.getLength(p2, y) > baseLength) {
            p2 = null;
        }
        Point3D result;
        if (p1 == null && p2 == null) {
            return Optional.empty();
        } else if (p1 == null) {
            result = p2;
        } else if (p2 == null) {
            result = p1;
        } else {
            result = MathUtils.getLength(x, p1) < MathUtils.getLength(x, p2) ? p1 : p2;
        }
        var diff = result.minus(center).multiply(0.03);
        diff = new Point3D(Math.min(diff.getX(), 10), Math.min(diff.getY(), 10), Math.min(diff.getZ(), 10));
        return Optional.of(result.plus(diff));
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Point3D getNormalVector(Point3D collision) {
        return collision.minus(center).normalized();
    }
}
