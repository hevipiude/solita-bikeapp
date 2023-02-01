package com.solita.citybike.singlestations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Stations class is annotated with @Entity, indicating that it is a JPA entity.
@Entity
public class S_Station {

    // The class has an ID field, indicating that it is the primary key
    @Id
    private int id;

    private String name_fin;

    private String address_fin;

    private int departure_count;

    private int return_count;

    public int getId() {
        return id;
    }

    public String getAddress_fin() {
        return address_fin;
    }

    public String getName_fin() {
        return name_fin;
    }

    public int getDeparture_count() {
        return departure_count;
    }

    public int getReturn_count() {
        return return_count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress_fin(String address_fin) {
        this.address_fin = address_fin;
    }

    public void setName_fin(String name_fin) {
        this.name_fin = name_fin;
    }

    public void setDeparture_count(int departure_count) {
        this.departure_count = departure_count;
    }

    public void setReturn_count(int return_count) {
        this.return_count = return_count;
    }
}
