package com.solita.citybike.stations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// The StationsController class handles HTTP requests with @RestController annotation. It uses @Autowired to inject a StationsService instance variable.
@RestController
public class StationsController {

    @Autowired
    private StationsService stationService;

    // The class has a @GetMapping("/stations") method which returns paginated data
    // of type Stations. The method calls the stationService's
    // getStationsByPagination()
    // method with a pageNumber, pageSize and Pageable object as parameter for
    // pagination.

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/stations")
    public List<Stations> getPagedStations(@RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize, Pageable pageable) {
        System.out.println(pageNumber + " " + pageSize);
        return stationService.getStationsByPagination(pageNumber, pageSize);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/singlestation")
    public Optional<Stations> getStationByID(@RequestParam(defaultValue = "0") int id) {
        System.out.println(id);
        return stationService.getStationsByID(id);
    }

}
