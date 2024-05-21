package com.ganghuang.mlc2_android;

public class TestFruit {
    private String name;
    private int imageId;
    public TestFruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }
}