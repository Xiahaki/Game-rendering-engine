package org.example;

import java.awt.*;
import java.awt.image.ImageObserver;

public class ImageObserverImpl implements ImageObserver {
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
}
