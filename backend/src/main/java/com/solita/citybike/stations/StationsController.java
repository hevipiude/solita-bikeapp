package com.solita.citybike.stations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// The StationsController class handles HTTP requests with @RestController annotation. It uses @Autowired to inject a StationsService instance variable.
@RestController
public class StationsController {

    @Autowired
    private StationsService stationService;

    // The class has a @GetMapping("/stations") method which returns paginated data
    // of type Stations. The method calls the stationService's getAllStations()
    // method with a Pageable object as parameter for pagination.

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/stations")
    public Page<Stations> getAllStations(Pageable pageable) {
        return stationService.getAllStations(pageable);
    }
}
