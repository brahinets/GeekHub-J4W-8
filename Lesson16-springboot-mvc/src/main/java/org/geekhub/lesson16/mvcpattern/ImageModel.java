package org.geekhub.lesson16.mvcpattern;

public class ImageModel {
    private int width;
    private int height;
    private String format;
    private byte[] binaryContent;

    public ImageModel(int width, int height, String format) {
        this.width = width;
        this.height = height;
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public byte[] getBinaryContent() {
        return binaryContent;
    }

    public void setBinaryContent(byte[] binaryContent) {
        this.binaryContent = binaryContent;
    }
}
