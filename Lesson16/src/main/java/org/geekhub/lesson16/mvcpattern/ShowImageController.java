package org.geekhub.lesson16.mvcpattern;

import org.geekhub.lesson16.mvcpattern.views.View;

public class ShowImageController {
    private View<ImageModel> view;
    private ImageModel model;

    public ShowImageController(View<ImageModel> view, ImageModel model) {
        this.view = view;
        this.model = model;
    }

    public int getWidth() {
        return model.getWidth();
    }

    public void setWidth(int width) {
        model.setWidth(width);
    }

    public int getHeight() {
        return model.getHeight();
    }

    public void setHeight(int height) {
        model.setHeight(height);
    }

    public String getFormat() {
        return model.getFormat();
    }

    public void setFormat(String format) {
        model.setFormat(format);
    }

    public byte[] getBinaryContent() {
        return model.getBinaryContent();
    }

    public void setBinaryContent(byte[] binaryContent) {
        model.setBinaryContent(binaryContent);
    }

    public void setView(View<ImageModel> view) {
        this.view = view;
    }

    public void updateView() {
        view.show(model);
    }
}
