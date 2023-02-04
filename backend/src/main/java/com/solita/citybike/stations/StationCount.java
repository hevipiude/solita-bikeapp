package com.solita.citybike.stations;

public interface StationCount {

    Integer getFid();

    Integer getStationId();

    String getNameFin();

    String getNameSwe();

    String getNameEng();

    String getAddressFin();

    String getAddressSwe();

    String getCityFin();

    String getCitySwe();

    String getOperator();

    Integer getCapacity();

    double getX();

    double getY();

    Integer getDepartureCount();

    Integer getReturnCount();

}
