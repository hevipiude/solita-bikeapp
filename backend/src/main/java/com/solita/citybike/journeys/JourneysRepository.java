package com.solita.citybike.journeys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneysRepository extends JpaRepository<Journeys, Long> {
}
