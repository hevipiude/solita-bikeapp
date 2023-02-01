package com.solita.citybike.singlestations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
// JourneyService class is annotated with @Service ti indicate that it is
// retrieving journey data from a database
public class S_StationsService {

    // The class has an instance variable of JourneyRepository, which is annotated
    // with @Autowired to inject the repository class
    @Autowired
    private S_StationsRepository stationsRepository;

    public List<S_Station> getS_StationsByPagination(int pageNo, int pageSize) {

        // create pagerequest object
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        // pass it to repos
        Page<S_Station> pagingStations = stationsRepository.findAll(pageRequest);
        // pagingStations.getContent(); -- to check pages are there or not
        return pagingStations.getContent();
    }

    public Optional<S_Station> getS_StationByID(int id) {

        // pass id to repos
        Optional<S_Station> station = stationsRepository.findById(id);
        // pagingStations.getContent(); -- to check pages are there or not
        return station;
    }

}
