package com.solita.citybike.journeys;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Journeys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private Timestamp departureTime;

    private Timestamp returnTime;

    private Integer departureStationId;

    private String departureStationName;

    private Integer returnStationId;

    private String returnStationName;

    private Integer distance;

    private Integer duration;

    public Long getID() {
        return ID;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public Integer getDepartureStationId() {
        return departureStationId;
    }

    public String getDepartureStationName() {
        return departureStationName;
    }

    public Integer getReturnStationId() {
        return returnStationId;
    }

    public String getReturnStationName() {
        return returnStationName;
    }

    public Integer getDistance() {
        return distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }

    public void setDepartureStationId(Integer departureStationId) {
        this.departureStationId = departureStationId;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public void setReturnStationId(Integer returnStationId) {
        this.returnStationId = returnStationId;
    }

    public void setReturnStationName(String returnStationName) {
        this.returnStationName = returnStationName;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
