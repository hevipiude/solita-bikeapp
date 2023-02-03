package com.solita.citybike.stations;

public interface StationCount {

    int getFid();

    int getStation_id();

    String getName_fin();

    String getName_swe();

    String getName_eng();

    String getAddress_fin();

    String getAddress_swe();

    String getCity_fin();

    String getCity_swe();

    String getOperator();

    int getCapacity();

    double getX();

    double getY();

    long getDeparture_count();

    long getReturn_count();

}
