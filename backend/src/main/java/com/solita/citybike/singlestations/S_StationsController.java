package com.solita.citybike.singlestations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// The S_StationsController class handles HTTP requests with @RestController annotation. It uses @Autowired to inject a StationsService instance variable.
@RestController
public class S_StationsController {

    @Autowired
    private S_StationsService stationService;

    // The class has a @GetMapping("/stations") method which returns paginated data
    // of type Stations. The method calls the stationService's
    // getStationsByPagination()
    // method with a pageNumber, pageSize and Pageable object as parameter for
    // pagination.

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/s_stations")
    public List<S_Station> getPagedStations(@RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "100") Integer pageSize, Pageable pageable) {
        System.out.println(pageNumber + " " + pageSize);
        return stationService.getS_StationsByPagination(pageNumber, pageSize);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/s_station")
    public Optional<S_Station> getStationByID(@RequestParam(defaultValue = "0") Integer id) {
        System.out.println(id);
        return stationService.getS_StationByID(id);
    }

}
