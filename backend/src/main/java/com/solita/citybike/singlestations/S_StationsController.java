package com.solita.citybike.singlestations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class S_StationsController {

    @Autowired
    private S_StationsService stationService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/s_stations")
    public List<S_Station> getPagedStations(@RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "100") Integer pageSize, Pageable pageable) {
        return stationService.getS_StationsByPagination(pageNumber, pageSize);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/s_station")
    public Optional<S_Station> getStationByID(@RequestParam(defaultValue = "0") Integer id) {
        return stationService.getS_StationByID(id);
    }

}
