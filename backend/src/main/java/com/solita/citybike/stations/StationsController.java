package com.solita.citybike.stations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationsController {

    @Autowired
    private StationsService stationService;

    // @CrossOrigin(origins = "*", allowedHeaders = "*")
    // @GetMapping("/stations")
    // public Page<Stations> getPagedStations(@RequestParam(defaultValue = "0")
    // Integer pageNumber,
    // @RequestParam(defaultValue = "10") Integer pageSize, Pageable pageable) {
    // return stationService.getStationsByPagination(pageNumber, pageSize);
    // }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/station")
    public StationCount getSingleStationByID(@RequestParam(defaultValue = "0") int id) {
        return stationService.getStationCountById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/stations")
    public Page<Stations> getPagedSortedStations(@RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "stationId") String sort, @RequestParam(defaultValue = "des") String order) {
        return stationService.getStationsByPageSort(pageNumber, pageSize, sort, order);
    }

}
