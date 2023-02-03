package com.solita.citybike.journeys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface JourneysRepository extends JpaRepository<Journeys, Long> {
    @Query(value = "SELECT * FROM JOURNEYS", nativeQuery = true)
    Page<Journeys> findAllJourneys(int pageNo, int pageSize, Pageable pageable);

}
