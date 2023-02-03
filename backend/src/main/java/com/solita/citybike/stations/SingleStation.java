package com.solita.citybike.stations;

public interface SingleStation {

    int getStation_id();

    String getName_fin();

    String getAddress_fin();

    long getDeparture_count();

    long getReturn_count();

}
