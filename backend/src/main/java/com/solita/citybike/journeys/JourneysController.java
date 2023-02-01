package com.solita.citybike.journeys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// The JourneyController class handles HTTP requests with @RestController annotation. It uses @Autowired to inject a JourneyService instance variable.
@RestController
public class JourneysController {

    @Autowired
    private JourneysService journeyService;

    // The class has a @GetMapping("/journeys") method which returns paginated data
    // of type Journeys. The method calls the journeyService's getAllJourneys()
    // method with a Pageable object as parameter for pagination.

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/journeys")
    public List<Journeys> getPagedStations(@RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize, Pageable pageable) {
        System.out.println(pageNumber + " " + pageSize);
        return journeyService.getJourneysByPagination(pageNumber, pageSize);
    }
}
