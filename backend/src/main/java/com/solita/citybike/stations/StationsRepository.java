package com.solita.citybike.stations;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationsRepository extends PagingAndSortingRepository<Stations, Long> {

    @Query(value = "with dep as (select journeys.departure_station_id,count(departure_station_id) departure_count from journeys group by departure_station_id), ret as (select journeys.return_station_id,count(return_station_id) return_count from journeys group by return_station_id) select fid, station_id AS stationId, name_fin AS nameFin, name_swe AS nameSwe, name_eng AS nameEng, address_fin AS addressFin, address_swe AS addressSwe, city_fin AS cityFin, city_swe AS citySwe, operator, capacity, x, y, departure_count AS departureCount, return_count AS returnCount from stations left join dep on stations.station_id=dep.departure_station_id left join ret on stations.station_id=ret.return_station_id WHERE stations.station_id = (:id)", nativeQuery = true)
    StationCount getStationCountById(int id);
}
