package org.example;

public class Point3D {
    private final double x;
    private final double y;
    private final double z;

    public static Point3D zero() {
        return new Point3D(0, 0, 0);
    }

    public Point3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Point3D multiply(final Point3D other) {
        return new Point3D(other.getX() * this.getX(), other.getY() * this.getY(), other.getZ() * this.getZ());
    }

    public Point3D multiply(final double x) {
        return new Point3D((x * this.getX()), (x * this.getY()), (x * this.getZ()));
    }

    public Point3D plus(final Point3D other) {
        return new Point3D(other.getX() + this.getX(), other.getY() + this.getY(), other.getZ() + this.getZ());
    }

    public Point3D minus(final Point3D other) {
        return new Point3D(this.getX() - other.getX(), this.getY() - other.getY(), this.getZ() - other.getZ());
    }

    public Point3D normalized() {
        return this.multiply(1.0 / MathUtils.getLength(this, new Point3D(0, 0, 0)));
    }

    public double dot(Point3D point) {
        return x * point.getX() + y * point.getY() + z * point.getZ();
    }
}
