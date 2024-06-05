package com.example.androidfoodapp.model;

import java.util.List;

public class Packages {
    private String phone;
    private String name;
    private String pord;
    private String address;

    private String date;

    private String time;
    private List<Orders> items;

    public Packages() {
    }

    public Packages(String phone, String name, String pord, String address, String date, String time, List<Orders> items) {
        this.phone = phone;
        this.name = name;
        this.pord = pord;
        this.address = address;
        this.date = date;
        this.time = time;
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getPord() {
        return pord;
    }

    public String getAddress() {
        return address;
    }

    public List<Orders> getItems() {
        return items;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPord(String pord) {
        this.pord = pord;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setItems(List<Orders> items) {
        this.items = items;
    }
}


