package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Painter extends JFrame {
    private final Canvas canvas;

    public Painter(BufferedImage img, int width, int height) {
        super("canvas");
        canvas = new Canvas() {

            public void paint(Graphics g) {
                g.drawImage(img, 0, 0, new ImageObserverImpl());
            }
        };


        // set background
        canvas.setBackground(Color.white);

        add(canvas);
        setSize(width, height);
        show();
    }

    public void repaint() {
        canvas.repaint();
    }
}
