package com.solita.citybike.stations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StationsController {

    @Autowired
    private StationsService stationService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/stations")
    public Page<Stations> getPagedStations(@RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "100") Integer pageSize, Pageable pageable) {
        return stationService.getStationsByPagination(pageNumber, pageSize);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/singlestation")
    public Optional<Stations> getStationByID(@RequestParam(defaultValue = "0") int id) {
        return stationService.getStationsByID(id);
    }

}
