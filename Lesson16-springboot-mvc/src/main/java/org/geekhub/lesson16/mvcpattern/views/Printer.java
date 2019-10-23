package org.geekhub.lesson16.mvcpattern.views;

import org.geekhub.lesson16.mvcpattern.ImageModel;

public class Printer implements View<ImageModel> {
    @Override
    public void show(ImageModel data) {
        System.out.println("Get image of type " + data.getFormat() + " with sizes: " + data.getHeight() + "x" + data.getWidth());
        System.out.println("Sent image data to printer driver for printing.");
        System.out.println("Wait a minute.");
        System.out.println("Paper with image ready to use.");
        System.out.println();
    }
}
