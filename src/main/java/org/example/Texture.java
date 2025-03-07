package org.example;

import java.awt.*;

public class Texture {
    private Point3D ambient;
    private Point3D diffuse;
    private Point3D specular;

    private double shininess;

    public Texture(final Point3D ambient, final Point3D diffuse, final Point3D specular, final double shininess) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    public Color mapColor(Point3D channels) {
        int red = (int) (channels.getX() * 255);
        int green = (int) (channels.getY() * 255);
        int blue = (int) (channels.getZ() * 255);
        return new Color(red, green, blue);
    }


    public Color getColor(Point3D collision, Point3D normal, Point3D toCamera, Point3D toLightUnit, LightSource light) {
        var color = new Point3D(0, 0, 0);
        var diffuseFactor = toLightUnit.dot(normal);
        if (diffuseFactor < 0) {
            return mapColor(color);
        }
        color = (color.plus(ambient.multiply(light.getAmbient())));
        color = color.plus(diffuse.multiply(light.getDiffuse()).multiply(Math.max(0.0, diffuseFactor)));
        var h = (toCamera.plus(toLightUnit)).normalized();
        color = color.plus(specular.multiply(light.getSpecular()).multiply(Math.max(0.0,
                Math.pow(h.dot(normal), shininess / 4.0))
        ));
        color = new Point3D(MathUtils.coerceIn(color.getX(), 0.0, 1.0),
                MathUtils.coerceIn(color.getY(), 0.0, 1.0),
                MathUtils.coerceIn(color.getZ(), 0.0, 1.0)
        );
        return mapColor(color);
    }
}
