package com.mindlink.controllers;

import com.mindlink.Util.AuxiliarClasses.StudyGroupDTO;
import com.mindlink.models.StudyGroup;
import com.mindlink.services.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/studyGroups")
public class StudyGroupController {

    @Autowired
    private StudyGroupService studyGroupService;

    @PostMapping("addDescription/{groupId}")
    public ResponseEntity<?> postMethodName(@PathVariable String groupId, @RequestBody Map<String, String> body) {
        try {
            studyGroupService.addDescriptionToGroup(groupId, body);
            return ResponseEntity.ok().build();

        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    

    @PostMapping("/addContent/{groupId}")
    public ResponseEntity<Void> addContentToGroup(@PathVariable String groupId, @RequestBody Map<String, String> newContent) {
        try {
            studyGroupService.addContentToGroup(groupId, newContent);
            return ResponseEntity.ok().build();

        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    

    @GetMapping("/findByStudent/{id}")
    public List<StudyGroupDTO> geStudyGroupsByStudent(@PathVariable String id) {
        return studyGroupService.findGroupsByStudent(id);
    }
    
    @GetMapping("/getAllDTOs")
    public List<StudyGroupDTO> getAllDtos() {
        return studyGroupService.findAllDtos();
    }


    // Crear un nuevo grupo de estudio
    @PostMapping
    public ResponseEntity<StudyGroup> createStudyGroup(@RequestBody StudyGroup studyGroup) {
        StudyGroup savedGroup = studyGroupService.save(studyGroup);
        return ResponseEntity.ok(savedGroup);
    }

    // Obtener todos los grupos de estudio
    @GetMapping
    public ResponseEntity<List<StudyGroup>> getAllStudyGroups() {
        List<StudyGroup> groups = studyGroupService.findAll();
        return ResponseEntity.ok(groups);
    }

    // Obtener un grupo de estudio por ID
    @GetMapping("/{id}")
    public ResponseEntity<StudyGroup> getStudyGroupById(@PathVariable String id) {
        Optional<StudyGroup> studyGroup = studyGroupService.findById(id);
        return studyGroup.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un grupo de estudio
    @PutMapping("/{id}")
    public ResponseEntity<StudyGroup> updateStudyGroup(@PathVariable String id, @RequestBody StudyGroup studyGroup) {
        StudyGroup updatedGroup = studyGroupService.update(id, studyGroup);
        return updatedGroup != null ? ResponseEntity.ok(updatedGroup) : ResponseEntity.notFound().build();
    }

    // Eliminar un grupo de estudio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyGroup(@PathVariable String id) {
        boolean deleted = studyGroupService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
