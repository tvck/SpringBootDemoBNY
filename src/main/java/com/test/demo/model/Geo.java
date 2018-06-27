package com.test.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="geo")
public class Geo {
    @Id
    @GenericGenerator(name = "gen",strategy = "increment")
    @GeneratedValue(generator = "gen")
    private int id;
    @Column
    private double lat;
    @Column
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat:" + lat +
                ", lng:" + lng +
                '}';
    }
}
