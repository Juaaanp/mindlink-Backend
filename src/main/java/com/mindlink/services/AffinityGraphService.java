package com.mindlink.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindlink.Util.AffinityGraph.AffinityGraph;
import com.mindlink.models.StudyGroup;
import com.mindlink.repositories.StudentRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AffinityGraphService {

    @Autowired
    private final StudyGroupService studyGroupService;
    @Autowired
    private final StudentRepository studentRepository;

    public AffinityGraphService(StudyGroupService studyGroupService,
                                StudentRepository studentRepository) {
        this.studyGroupService = studyGroupService;
        this.studentRepository = studentRepository;
    }

    /**
     * Reconstruye el grafo de afinidad cada vez que la aplicación
     * arranca para que persista entre reinicios.
     */
    @PostConstruct
    public void loadGraphOnStartup() {
        rebuild();   // delegamos en el método rebuild() para no duplicar código
    }

    /** Reconstruye el grafo leyendo los grupos desde la base de datos. */
    public void rebuild() {
        List<StudyGroup> groups = studyGroupService.findAll();

        // Recrea el grafo en memoria a partir de los grupos
        AffinityGraph.getInstance().buildGraphFromStudyGroups(
            groups,
            id -> studentRepository.findById(id).orElse(null)   // resuelve cada ID a Student
        );
    }
}


