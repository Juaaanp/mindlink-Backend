package com.mindlink.repositories;

import com.mindlink.Util.DoublyLinkedList.DoublyLinkedList;
import com.mindlink.models.Content;
import com.mindlink.models.Student;
import com.mindlink.models.StudyGroup;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository extends MongoRepositoryImpl<Student> {

    public StudentRepository() {
        super(Student.class);
    }

    public Student cargarEstudianteConGrupos(String id) {
        Student estudiante = mongoTemplate.findById(id, Student.class);
        if (estudiante != null && estudiante.getStudyGroupsIdList() != null) {
            Query query = new Query(Criteria.where("_id").in(estudiante.getStudyGroupsIdList()));
            List<StudyGroup> grupos = mongoTemplate.find(query, StudyGroup.class); // Encuentra los grupos de un estudiante
        
            // Convertir la lista de grupos en tu lista enlazada propia
            DoublyLinkedList<StudyGroup> listaPropia = new DoublyLinkedList<>();
            for (StudyGroup grupo : grupos) {
                listaPropia.addEnding(grupo);
            }
    
            estudiante.setStudyGroupsOwnList(listaPropia);  // Guardas tu lista propia en el objeto
        }
        return estudiante;
    }

    public Student cargarEstudianteConContenidos(String id) {
        Student estudiante = mongoTemplate.findById(id, Student.class);
        if (estudiante != null) {
            Query query = new Query(Criteria.where("autorId").is(id));
            List<Content> contenidos = mongoTemplate.find(query, Content.class);

            DoublyLinkedList<Content> listaPropia = new DoublyLinkedList<>();
            for (Content contenido : contenidos) {
                listaPropia.addEnding(contenido);
            }
            estudiante.setPublishedContentsOwnList(listaPropia); //Asignas en la lista @Transient
        }
        return estudiante;
    }
}
