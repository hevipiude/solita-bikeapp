package com.solita.citybike.journeys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// The interface is annotated with @Repository, indicating it is a repository class for handling data operations.
@Repository
// findAllJourneys() is annotated with @Query to execute a SELECT query to fetch
// all journeys from the JOURNEYS table.
public interface JourneysRepository extends JpaRepository<Journeys, Long> {
    @Query(value = "SELECT * FROM JOURNEYS", nativeQuery = true)
    Page<Journeys> findAllJourneys(int pageNo, int pageSize, Pageable pageable);

}
