package com.solita.citybike.stations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
// JourneyService class is annotated with @Service ti indicate that it is
// retrieving journey data from a database
public class StationsService {

    // The class has an instance variable of JourneyRepository, which is annotated
    // with @Autowired to inject the repository class
    @Autowired
    private StationsRepository stationsRepository;

    public Page<Stations> getStationsByPagination(int pageNo, int pageSize) {

        // create pagerequest object
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        // pass it to repos
        return stationsRepository.findAll(pageRequest);
    }

    public Optional<Stations> getStationsByID(int id) {

        // pass id to repos
        Optional<Stations> station = stationsRepository.findById(id);
        // pagingStations.getContent(); -- to check pages are there or not
        return station;
    }
}
