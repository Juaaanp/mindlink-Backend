package com.mindlink.services;

import com.mindlink.Util.AuxiliarClasses.StudyGroupDTO;
import com.mindlink.exceptions.NullOrVoidException;
import com.mindlink.models.StudyGroup;
import com.mindlink.repositories.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudyGroupService {

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    public void addDescriptionToGroup(String groupId, Map<String, String> body) {

        String newDescription = body.get("description");
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new NullOrVoidException("Descripción nula o vacía");
        }
        studyGroupRepository.addDescriptionToGroup(groupId, newDescription);
    }

    public void addContentToGroup(String groupId, Map<String, String> body) { //content: newContent
        String newContent = body.get("content");
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new NullOrVoidException("Contenido nulo o vacío");
        }
        studyGroupRepository.addContentToGroup(groupId, newContent);
    }

    public List<StudyGroupDTO> findGroupsByStudent(String id) {
        return studyGroupRepository.findGroupsByStudent(id);
    }

    public List<StudyGroupDTO> findAllDtos() {
        return studyGroupRepository.getAllGroupDTOs();
    }

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
