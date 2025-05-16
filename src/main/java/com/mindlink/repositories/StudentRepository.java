package com.mindlink.repositories;

import com.mindlink.Util.SimplyLinkedList.SimplyLinkedList;
import com.mindlink.models.Content;
import com.mindlink.models.Student;
import com.mindlink.models.StudyGroup;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository extends MongoRepositoryImpl<Student> {

    public StudentRepository() {
        super(Student.class);
    }

    public Student findByEmailAndPassword(String email, String password) {
        Query query = new Query(
            Criteria.where("email").is(email)
                    .and("password").is(password)
        );
        return mongoTemplate.findOne(query, Student.class);
    }
    
    public Optional<Student> findByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return Optional.ofNullable(mongoTemplate.findOne(query, Student.class));
    }

    public Student cargarEstudianteConGrupos(String id) {
        Student estudiante = mongoTemplate.findById(id, Student.class);
        if (estudiante != null && estudiante.getStudyGroupsIdList() != null) {
            Query query = new Query(Criteria.where("_id").in(estudiante.getStudyGroupsIdList()));
            List<StudyGroup> grupos = mongoTemplate.find(query, StudyGroup.class); // Encuentra los grupos de un estudiante
        
            // Convertir la lista de grupos en tu lista enlazada propia
            SimplyLinkedList<StudyGroup> listaPropia = new SimplyLinkedList<>();
            for (StudyGroup grupo : grupos) {
                listaPropia.addLast(grupo);
            }
    
            estudiante.setStudyGroupsOwnList(listaPropia);  // Guardas tu lista propia en el objeto
        }
        return estudiante;
    }

    public Student cargarEstudianteConContenidos(String id) {
        Student estudiante = mongoTemplate.findById(id, Student.class);
        if (estudiante != null) {
            Query query = new Query(Criteria.where("authorId").is(id));
            List<Content> contenidos = mongoTemplate.find(query, Content.class);

            SimplyLinkedList<Content> listaPropia = new SimplyLinkedList<>();
            if (contenidos.isEmpty()) System.out.println("no hay contents");
            for (Content contenido : contenidos) {
                listaPropia.addLast(contenido); //Meto los contenidos de la query en la lista propia
                System.out.println("contenido encontrado");
            }
            estudiante.setPublishedContentsOwnList(listaPropia); //Asigna en la lista @Transient
        }
        return estudiante;
    }

    public boolean existsByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, Student.class);
    }

    
}
