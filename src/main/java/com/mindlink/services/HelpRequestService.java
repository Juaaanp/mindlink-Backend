package com.mindlink.services;

import com.mindlink.models.HelpRequest;
import com.mindlink.repositories.HelpRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpRequestService {

    @Autowired
    private HelpRequestRepository helpRequestRepository;

    public HelpRequest save(HelpRequest helpRequest) {
        return helpRequestRepository.save(helpRequest);
    }

    public List<HelpRequest> findAll() {
        return helpRequestRepository.findAll();
    }

    public Optional<HelpRequest> findById(String id) {
        return helpRequestRepository.findById(id);
    }

    public HelpRequest update(String id, HelpRequest updatedHelpRequest) {
        if (helpRequestRepository.findById(id).isPresent()) {
            updatedHelpRequest.setId(id);
            return helpRequestRepository.save(updatedHelpRequest);
        }
        return null;
    }

    public boolean delete(String id) {
        if (helpRequestRepository.findById(id).isPresent()) {
            helpRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
