package com.solita.citybike.stations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Stations {

    @Id
    private int fid;

    private int station_id;

    private String name_fin;

    private String name_swe;

    private String name_eng;

    private String address_fin;

    private String address_swe;

    private String city_fin;

    private String city_swe;

    private String operator;

    private int capacity;

    private double x;

    private double y;

    public int getFid() {
        return fid;
    }

    public int getStation_id() {
        return station_id;
    }

    public String getName_fin() {
        return name_fin;
    }

    public String getName_swe() {
        return name_swe;
    }

    public String getName_eng() {
        return name_eng;
    }

    public String getAddress_fin() {
        return address_fin;
    }

    public String getAddress_swe() {
        return address_swe;
    }

    public String getCity_fin() {
        return city_fin;
    }

    public String getCity_swe() {
        return city_swe;
    }

    public String getOperator() {
        return operator;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public void setName_fin(String name_fin) {
        this.name_fin = name_fin;
    }

    public void setName_swe(String name_swe) {
        this.name_swe = name_swe;
    }

    public void setName_eng(String name_eng) {
        this.name_eng = name_eng;
    }

    public void setAddress_fin(String address_fin) {
        this.address_fin = address_fin;
    }

    public void setAddress_swe(String address_swe) {
        this.address_swe = address_swe;
    }

    public void setCity_fin(String city_fin) {
        this.city_fin = city_fin;
    }

    public void setCity_swe(String city_swe) {
        this.city_swe = city_swe;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
