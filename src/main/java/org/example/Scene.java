package org.example;

import java.awt.*;
import java.util.List;

public class Scene {
    private List<GeometryUnit> unitList;

    private List<LightSource> lightSources;

    public Scene(final List<GeometryUnit> unitList, final List<LightSource> lightSources) {
        this.unitList = unitList;
        this.lightSources = lightSources;
    }

    public List<GeometryUnit> getUnitList() {
        return unitList;
    }

    public List<LightSource> getLightSources() {
        return lightSources;
    }

    private Color getColor(GeometryUnit geometryUnit, Point3D camera, Point3D collision) {
        for (var light : lightSources) {
            for (var unit : unitList) {
                var shadow = unit.getCollision(collision, light.getCenter());
                if (shadow.isPresent()) {
                    return Color.BLACK;
                }
            }
            var normal = geometryUnit.getNormalVector(collision);
            var toLightUnit = (light.getCenter().minus(collision)).normalized();
            var toCamera = (camera.minus(collision)).normalized();
            return geometryUnit.getTexture().getColor(collision, normal, toCamera, toLightUnit, light);
        }
        return Color.BLACK;
    }

    public Color traceRay(Point3D x, Point3D y) {
        GeometryUnit min = null;
        double minLength = 0;
        Point3D collision = null;
        for (var u : unitList) {
            var coll = u.getCollision(x, y);
            if (coll.isEmpty()) {
                continue;
            }
            var length = MathUtils.getLength(x, coll.get());
            if (min == null || length < minLength) {
                minLength = length;
                min = u;
                collision = coll.get();
            }
        }
        if (min != null) {
            return getColor(min, x, collision);
        }
        return new Color(0, 0, 0, 125);
    }

}
