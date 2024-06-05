package com.example.androidfoodapp.model;

public class Item {

    //Declaring variables from Items table
    private String Name;
    private String Image;
    private String Description;
    private String CatId;

    public Item() {
    }

    public Item(String name, String image, String description, String catId) {
        Name = name;
        Image = image;
        Description = description;
        CatId = catId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCatId() {
        return CatId;
    }

    public void setCatId(String catId) {
        CatId = catId;
    }
}
