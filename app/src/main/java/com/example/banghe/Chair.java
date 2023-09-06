package com.example.banghe;

public class Chair {
    private int image;
    private String name;
    private String cost;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Chair(int image, String name, String cost) {
        this.image = image;
        this.name = name;
        this.cost = cost;
    }
}
