package com.example.androidfoodapp.model;

public class Orders {
    private String ItemsID;
    private String ItemName;
    private String Quantity;

    public Orders() {
    }

    public Orders(String itemsID, String itemName, String quantity) {
        ItemsID = itemsID;
        ItemName = itemName;
        Quantity = quantity;
    }

    public String getItemsID() {
        return ItemsID;
    }

    public void setItemsID(String itemsID) {
        ItemsID = itemsID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}


