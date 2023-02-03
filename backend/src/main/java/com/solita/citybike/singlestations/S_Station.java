package com.solita.citybike.singlestations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class S_Station {

    @Id
    private Integer id;

    private String name_fin;

    private String address_fin;

    private Integer departure_count;

    private Integer return_count;

    public Integer getId() {
        return id;
    }

    public String getAddress_fin() {
        return address_fin;
    }

    public String getName_fin() {
        return name_fin;
    }

    public Integer getDeparture_count() {
        return departure_count;
    }

    public Integer getReturn_count() {
        return return_count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddress_fin(String address_fin) {
        this.address_fin = address_fin;
    }

    public void setName_fin(String name_fin) {
        this.name_fin = name_fin;
    }

    public void setDeparture_count(Integer departure_count) {
        this.departure_count = departure_count;
    }

    public void setReturn_count(Integer return_count) {
        this.return_count = return_count;
    }
}
