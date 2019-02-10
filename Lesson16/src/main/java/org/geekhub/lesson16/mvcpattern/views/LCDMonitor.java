package org.geekhub.lesson16.mvcpattern.views;

import org.geekhub.lesson16.mvcpattern.ImageModel;

public class LCDMonitor implements View<ImageModel> {
    @Override
    public void show(ImageModel data) {
        System.out.println("Get image of type " + data.getFormat() + " with sizes: " + data.getHeight() + "x" + data.getWidth());
        System.out.println("Convert image to video signal.");
        System.out.println("Sent video signal to LCD monitor.");
        System.out.println();
    }
}
