package com.example.entryapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String name;
    private String arrival;
    private String departure;
    private String place;
    private String here;


    public User() {
    }

    public User(String name, String arrival, String departure, String place, String here) {
        this.name = name;
        this.arrival = arrival;
        this.departure = departure;
        this.place = place;
        this.here = here;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHere() {
        return here;
    }

    public void setHere(String here) {
        this.here = here;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departure='" + departure + '\'' +
                ", place='" + place + '\'' +
                ", here='" + here + '\'' +
                '}';
    }
}
