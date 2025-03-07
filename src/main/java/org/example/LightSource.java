package org.example;

public class LightSource {
    private Point3D center;
    private Point3D ambient;
    private Point3D diffuse;
    private Point3D specular;


    public LightSource(final Point3D center, final Point3D ambient, final Point3D diffuse, final Point3D specular) {
        this.center = center;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    public Point3D getCenter() {
        return center;
    }

    public Point3D getAmbient() {
        return ambient;
    }

    public Point3D getDiffuse() {
        return diffuse;
    }

    public Point3D getSpecular() {
        return specular;
    }

    public void setCenter(final Point3D center) {
        this.center = center;
    }
}
