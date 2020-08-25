package com.example.entryapp;

public class Place {
    private String place_name;
    private String key;

    public Place() {
    }

    public Place(String place_name, String key) {
        this.place_name = place_name;
        this.key = key;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
