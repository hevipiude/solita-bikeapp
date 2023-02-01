package com.solita.citybike.journeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
// JourneyService class is annotated with @Service ti indicate that it is
// retrieving journey data from a database
public class JourneysService {

    // The class has an instance variable of JourneyRepository, which is annotated
    // with @Autowired to inject the repository class
    @Autowired
    private JourneysRepository journeyRepository;

    public Page<Journeys> getJourneysByPagination(int pageNo, int pageSize) {

        // create pagerequest object
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        // pass it to repos
        return journeyRepository.findAll(pageRequest);
    }
}
