package com.solita.citybike.singlestations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class S_StationsService {

    @Autowired
    private S_StationsRepository stationsRepository;

    public List<S_Station> getS_StationsByPagination(int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<S_Station> pagingStations = stationsRepository.findAll(pageRequest);

        return pagingStations.getContent();
    }

    public Optional<S_Station> getS_StationByID(Integer id) {

        Optional<S_Station> station = stationsRepository.findById(id);
        
        return station;
    }

}
