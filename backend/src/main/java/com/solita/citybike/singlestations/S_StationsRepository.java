package com.solita.citybike.singlestations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface S_StationsRepository extends JpaRepository<S_Station, Long> {
    @Query(value = "SELECT * FROM S_STATION", nativeQuery = true)

    Page<S_Station> findAllStations(int pageNo, int pageSize, Pageable pageable);

    @Query(value = "SELECT id, name_fin, address_fin,  Coalesce(departure_count, 0) AS departure_count, Coalesce(return_count, 0) AS return_count FROM s_station WHERE id = (:id)", nativeQuery = true)
    Optional<S_Station> findById(int id);
}