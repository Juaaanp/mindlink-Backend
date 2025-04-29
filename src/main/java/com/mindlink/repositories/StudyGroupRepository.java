package com.mindlink.repositories;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mindlink.Util.SimplyLinkedList.SimplyLinkedList;
import com.mindlink.models.Student;
import com.mindlink.models.StudyGroup;

@Repository
public class StudyGroupRepository extends MongoRepositoryImpl<StudyGroup>{
    public StudyGroupRepository() {
        super(StudyGroup.class);
    }

    public List<StudyGroup> cargarTodosGruposConEstudiantes() {
        List<StudyGroup> grupos = mongoTemplate.findAll(StudyGroup.class);

        for (StudyGroup grupo : grupos) {
            if (grupo.getStudentIdList() != null && !grupo.getStudentIdList().isEmpty()) {
                Query query = new Query(Criteria.where("_id").in(grupo.getStudentIdList()));
                List<Student> estudiantes = mongoTemplate.find(query, Student.class);

                // Crear lista propia
                SimplyLinkedList<Student> listaPropia = new SimplyLinkedList<>();
                for (Student estudiante : estudiantes) {
                    listaPropia.addLast(estudiante);
                }

                grupo.setStudentOwnList(listaPropia);
            }
        }
        return grupos;
    }
}
