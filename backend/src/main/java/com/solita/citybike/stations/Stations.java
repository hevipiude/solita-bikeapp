package com.solita.citybike.stations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stations {

    @Id
    private int fid;

    private int stationId;

    private String nameFin;

    private String nameSwe;

    private String nameEng;

    private String addressFin;

    private String addressSwe;

    private String cityFin;

    private String citySwe;

    private String operator;

    private int capacity;

    private double x;

    private double y;

    public int getFid() {
        return fid;
    }

    public int getStationId() {
        return stationId;
    }

    public String getNameFin() {
        return nameFin;
    }

    public String getNameSwe() {
        return nameSwe;
    }

    public String getNameEng() {
        return nameEng;
    }

    public String getAddressFin() {
        return addressFin;
    }

    public String getAddressSwe() {
        return addressSwe;
    }

    public String getCityFin() {
        return cityFin;
    }

    public String getCitySwe() {
        return citySwe;
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

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setNameFin(String nameFin) {
        this.nameFin = nameFin;
    }

    public void setNameSwe(String nameSwe) {
        this.nameSwe = nameSwe;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public void setAddressFin(String addressFin) {
        this.addressFin = addressFin;
    }

    public void setAddressSwe(String addressSwe) {
        this.addressSwe = addressSwe;
    }

    public void setCityFin(String cityFin) {
        this.cityFin = cityFin;
    }

    public void setCitySwe(String citySwe) {
        this.citySwe = citySwe;
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
