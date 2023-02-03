package com.solita.citybike.journeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Journeys> getPagedStations(@RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "100") Integer pageSize, Pageable pageable) {
        return journeyService.getJourneysByPagination(pageNumber, pageSize);
    }
}
