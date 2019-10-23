package org.geekhub.lesson16.mvcpattern;

import org.geekhub.lesson16.mvcpattern.views.LCDMonitor;
import org.geekhub.lesson16.mvcpattern.views.Printer;

public class Main {
    public static void main(String[] args) {
        final ImageModel model = new ImageModel(100, 200, "png");
        final LCDMonitor monitorView = new LCDMonitor();
        final Printer printerView = new Printer();

        final ShowImageController controller = new ShowImageController(monitorView, model);
        controller.updateView();

        controller.setFormat("jpg");
        controller.setHeight(1080);
        controller.setWidth(1920);
        controller.updateView();

        controller.setView(printerView);
        controller.updateView();
    }
}
