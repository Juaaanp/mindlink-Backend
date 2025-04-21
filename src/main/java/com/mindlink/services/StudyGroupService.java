package com.mindlink.services;

import com.mindlink.models.StudyGroup;
import com.mindlink.repositories.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyGroupService {

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    public StudyGroup save(StudyGroup studyGroup) {
        return studyGroupRepository.save(studyGroup);
    }

    public List<StudyGroup> findAll() {
        return studyGroupRepository.findAll();
    }

    public Optional<StudyGroup> findById(String id) {
        return studyGroupRepository.findById(id);
    }

    public StudyGroup update(String id, StudyGroup updatedGroup) {
        if (studyGroupRepository.findById(id).isPresent()) {
            updatedGroup.setId(id);
            return studyGroupRepository.save(updatedGroup);
        }
        return null;
    }

    public boolean delete(String id) {
        if (studyGroupRepository.findById(id).isPresent()) {
            studyGroupRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
