package com.solita.citybike.journeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JourneysService {

    @Autowired
    private JourneysRepository journeyRepository;

    public Page<Journeys> getJourneysByPageSort(int pageNo, int pageSize, String sort, String order) {

        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sort).descending());
        if (order.equals("asc")) {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());
        }
        Page<Journeys> pagedResult = journeyRepository.findAll(paging);

        return pagedResult;
    }

}
