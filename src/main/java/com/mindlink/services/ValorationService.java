package com.mindlink.services;

import com.mindlink.exceptions.NotFoundValorationException;
import com.mindlink.models.Valoration;
import com.mindlink.repositories.ValorationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValorationService {

    @Autowired
    private ValorationRepository valorationRepository;

    public List<Valoration> findByContent(String contentId) {
        List<Valoration> val = valorationRepository.findByContentId(contentId);
        if (val.isEmpty()) throw new NotFoundValorationException();
        return val;
    }

    public Valoration save(Valoration valoration) {
        return valorationRepository.save(valoration);
    }

    public List<Valoration> findAll() {
        return valorationRepository.findAll();
    }

    public Optional<Valoration> findById(String id) {
        return valorationRepository.findById(id);
    }

    public Valoration update(String id, Valoration updatedValoration) {
        if (valorationRepository.findById(id).isPresent()) {
            updatedValoration.setId(id);
            return valorationRepository.save(updatedValoration);
        }
        return null;
    }

    public boolean delete(String id) {
        if (valorationRepository.findById(id).isPresent()) {
            valorationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
