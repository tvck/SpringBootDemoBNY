package com.test.demo.model;

import javax.persistence.*;

@Entity
@Embeddable
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
