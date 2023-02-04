package com.solita.citybike.stations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StationsService {

    @Autowired
    private StationsRepository stationsRepository;

    public Page<Stations> getStationsByPagination(int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

        return stationsRepository.findAll(pageRequest);
    }

    public Page<Stations> getStationsByPageSort(int pageNo, int pageSize, String sort, String order) {

        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sort).descending());
        if (order.equals("asc")) {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());
        }
        Page<Stations> pagedResult = stationsRepository.findAll(paging);

        return pagedResult;
    }

    public Optional<Stations> getStationsByID(int id) {

        Optional<Stations> station = stationsRepository.findById(id);
        return station;
    }

    public StationCount getStationCountById(int id) {
        StationCount station = stationsRepository.getStationCountById(id);
        return station;
    }
}
