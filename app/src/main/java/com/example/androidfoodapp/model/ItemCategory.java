package com.example.androidfoodapp.model;

public class ItemCategory {

    //Variables for fields within ItemCategory Table
    private String Category;
    private String Image;

    public ItemCategory() {
    }


    public ItemCategory(String category, String image) {
        Category = category;
        Image = image;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
