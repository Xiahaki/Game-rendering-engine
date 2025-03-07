package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static int HEIGHT = 1000;
    private final static int WIDTH = 1000;

    // Main Method
    public static void main(String args[]) {
        List<GeometryUnit> units = new ArrayList<>();
        units.add(new Sphere(new Point3D(-300, 1000/2, 1000), 1000,
                baseTexture(Color.WHITE)
        ));
        units.add(new Sphere(new Point3D(300, 300, 50), 50,
                baseTexture(Color.GREEN)
        ));


        units.add(new Sphere(new Point3D(350, 0, 200), 100,
                baseTexture(Color.CYAN)
        ));
        units.add(new Sphere(new Point3D(300, 500, 100), 100,
                baseTexture(Color.ORANGE)
        ));
        units.add(new Sphere(new Point3D(400, 200, 100), 100,
                baseTexture(Color.BLUE)
        ));
        units.add(new Sphere(new Point3D(700, 700, 100), 100,
                baseTexture(Color.RED)
        ));
        units.add(new Sphere(new Point3D(700, 700, 1500), 100,
                baseTexture(Color.ORANGE)
        ));
//        units.add(new Sphere(new Point3D(700, 700, 3000), 100,
//                baseTexture(Color.RED)
//        ));
//        units.add(new Sphere(new Point3D(700, 700, 6000), 100,
//                baseTexture(Color.ORANGE)
//        ));
//
//        units.add(new Sphere(new Point3D(700, 700, 8000), 100,
//                baseTexture(Color.GREEN)
//        ));
//
//        units.add(new Sphere(new Point3D(700, 700, 10000), 100,
//                baseTexture(Color.GREEN)
//        ));
//
//        units.add(new Sphere(new Point3D(700, 700, 12000), 100,
//                baseTexture(Color.CYAN)
//        ));
        var light = lightSource(
                new Point3D(100.0, -2000.0, -2800.0)
        );
        var scene = new Scene(units, List.of(light));
        var image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        var screen = new Screen(WIDTH, HEIGHT, 10000, 5, scene);
        screen.getImage(new Point3D(0000, 0000, -500),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                image
        );
        Painter c = new Painter(image, WIDTH, HEIGHT);
        double i = 0;
        double j = 0;
        int maxX = 1000;
        int maxY = 1500;
        int maxZ = 1000;
        while (true) {
            try {
                i += 0.2;
                j += 0.03;


//                light.setCenter(new Point3D((Math.sin(i) + 1) * maxX,
//                        0,
//                        ((Math.sin(i) + 1) * maxZ)-700));
                screen.getImage(new Point3D(300, 0000, -1000),
                        new Point3D(1, 0, (Math.sin(i) + 1)),
                        new Point3D(0, 1, 0),
                        image
                );
                c.repaint();
                Thread.sleep(50);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private static Texture baseTexture(Color color) {
        var comps = color.getComponents(new float[4]);
        double ambieant = 0.05;
        return new Texture(
                new Point3D(comps[0] * ambieant, comps[1] * ambieant, comps[2] * ambieant),
                new Point3D(comps[0], comps[1], comps[2]),
                new Point3D(1.0, 1.0, 1.0),
                100.0
        );
    }

    private static LightSource lightSource(Point3D center) {

        return new LightSource(
                center,
                new Point3D(0.5, 0.5, 0.5),
                new Point3D(1.5, 1.5, 1.5),
                new Point3D(1.0, 1.0, 1.0)
        );
    }
}