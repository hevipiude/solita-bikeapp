package com.solita.citybike.journeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JourneysController {

    @Autowired
    private JourneysService journeyService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/")
    public Page<Journeys> getPagedSortedJourneys(@RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "departureStationName") String sort, @RequestParam(defaultValue = "des") String order) {
        return journeyService.getJourneysByPageSort(pageNumber, pageSize, sort, order);
    }
}
