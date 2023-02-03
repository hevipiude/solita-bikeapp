package com.solita.citybike.journeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class JourneysService {

    @Autowired
    private JourneysRepository journeyRepository;

    public Page<Journeys> getJourneysByPagination(int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        
        return journeyRepository.findAll(pageRequest);
    }
}
