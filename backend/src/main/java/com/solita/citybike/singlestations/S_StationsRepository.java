package com.solita.citybike.singlestations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// The interface is annotated with @Repository, indicating it is a repository class for handling data operations.
@Repository
// findAllStations() is annotated with @Query to execute a SELECT query to fetch
// all stations from the STATIONS table.
public interface S_StationsRepository extends JpaRepository<S_Station, Long> {
    @Query(value = "SELECT * FROM S_STATION", nativeQuery = true)

    Page<S_Station> findAllStations(int pageNo, int pageSize, Pageable pageable);

    @Query(value = "SELECT * FROM S_STATION WHERE id = (:id)", nativeQuery = true)
    Optional<S_Station> findById(int id);
}