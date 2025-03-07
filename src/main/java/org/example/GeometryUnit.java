package org.example;

import java.util.Optional;

public interface GeometryUnit {
    Point3D getNormalVector(Point3D collision);

    Optional<Point3D> getCollision(Point3D x, Point3D y);

    Texture getTexture();
}
