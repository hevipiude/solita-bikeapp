package com.solita.citybike.stations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface StationsRepository extends JpaRepository<Stations, Long> {
    @Query(value = "SELECT * FROM STATIONS", nativeQuery = true)
    Page<Stations> findAllStations(int pageNo, int pageSize, Pageable pageable);

    @Query(value = "with dep as (select journeys.departure_station_id,count(departure_station_id) departure_count from journeys group by departure_station_id), ret as (select journeys.return_station_id,count(return_station_id) return_count from journeys group by return_station_id) select fid, station_id, name_fin, name_swe, name_eng, address_fin, address_swe, city_fin, city_swe, operator, capacity, x, y, departure_count, return_count from stations left join dep on stations.station_id=dep.departure_station_id left join ret on stations.station_id=ret.return_station_id WHERE stations.station_id = (:id)", nativeQuery = true)
    Optional<Stations> findById(int id);
}