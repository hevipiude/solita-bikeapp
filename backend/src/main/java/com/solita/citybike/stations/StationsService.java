package com.solita.citybike.stations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
// JourneyService class is annotated with @Service ti indicate that it is
// retrieving journey data from a database
public class StationsService {

    // The class has an instance variable of JourneyRepository, which is annotated
    // with @Autowired to inject the repository class
    @Autowired
    private StationsRepository stationRepository;

    // getAllJourneys() takes a Pageable object as a parameter which is used for
    // pagination of the returned data
    public Page<Stations> getAllStations(Pageable pageable) {
        // The method returns a Page object which contains the paginated data of type
        // Journeys
        return stationRepository.findAll(pageable);
    }
}
