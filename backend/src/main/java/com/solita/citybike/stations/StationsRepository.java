package com.solita.citybike.stations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// The interface is annotated with @Repository, indicating it is a repository class for handling data operations.
@Repository
// findAllStations() is annotated with @Query to execute a SELECT query to fetch
// all stations from the STATIONS table.
public interface StationsRepository extends JpaRepository<Stations, Long> {
    @Query(value = "SELECT * FROM STATIONS", nativeQuery = true)
    Page<Stations> findAllStations(Pageable pageable);

}