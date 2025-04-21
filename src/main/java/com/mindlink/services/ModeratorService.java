package com.mindlink.services;

import com.mindlink.models.Moderator;
import com.mindlink.repositories.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeratorService {

    @Autowired
    private ModeratorRepository moderatorRepository;

    public Moderator save(Moderator moderator) {
        return moderatorRepository.save(moderator);
    }

    public List<Moderator> findAll() {
        return moderatorRepository.findAll();
    }

    public Optional<Moderator> findById(String id) {
        return moderatorRepository.findById(id);
    }

    public Moderator update(String id, Moderator updatedModerator) {
        if (moderatorRepository.findById(id).isPresent()) {
            updatedModerator.setId(id);
            return moderatorRepository.save(updatedModerator);
        }
        return null;
    }

    public boolean delete(String id) {
        if (moderatorRepository.findById(id).isPresent()) {
            moderatorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
