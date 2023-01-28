package com.solita.citybike;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Journeys class is annotated with @Entity, indicating that it is a JPA entity.
@Entity
public class Journeys {

    // The class has an ID field, indicating that it is the primary key and it is
    // generated automatically.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;



    private Timestamp departure_time;

    private Timestamp return_time;

    private Integer departure_station_id;

    private String departure_station_name;

    private Integer return_station_id;

    private String return_station_name;

    private Integer distance;

    private Integer duration;
    
    public Long getID() {
        return ID;
    }

    public Timestamp getDeparture_time() {
        return departure_time;
    }

    public Timestamp getReturn_time() {
        return return_time;
    }

    public Integer getDeparture_station_id() {
        return departure_station_id;
    }

    public String getDeparture_station_name() {
        return departure_station_name;
    }

    public Integer getReturn_station_id() {
        return return_station_id;
    }

    public String getReturn_station_name() {
        return return_station_name;
    }

    public Integer getDistance() {
        return distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDeparture_time(Timestamp departure_time) {
        this.departure_time = departure_time;
    }

    public void setReturn_time(Timestamp return_time) {
        this.return_time = return_time;
    }

    public void setDeparture_station_id(Integer departure_station_id) {
        this.departure_station_id = departure_station_id;
    }

    public void setDeparture_station_name(String departure_station_name) {
        this.departure_station_name = departure_station_name;
    }

    public void setReturn_station_id(Integer return_station_id) {
        this.return_station_id = return_station_id;
    }

    public void setReturn_station_name(String return_station_name) {
        this.return_station_name = return_station_name;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
