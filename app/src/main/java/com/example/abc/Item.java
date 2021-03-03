package com.example.abc;

public class Item {
    public String sign,description;
    public int image;

    //constructor

    public Item(String sign, String description, int image) {
        this.sign = sign;
        this.description = description;
        this.image = image;
    }

    //generates setters and getters

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
