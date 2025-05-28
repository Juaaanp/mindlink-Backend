package com.mindlink.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mindlink.models.HelpRequest;
import com.mindlink.models.Student;
import com.mongodb.client.result.DeleteResult;

@Repository
public class HelpRequestRepository extends MongoRepositoryImpl<HelpRequest>{
    public HelpRequestRepository() {
        super(HelpRequest.class);
    }

    @Autowired
    private StudentRepository studentRepository;

    public List<HelpRequest> findAllWithStudentName() {

        List<HelpRequest> helpRequests = findAll();

        for (HelpRequest hr: helpRequests) {
            studentRepository.findById(hr.getStudent()).ifPresent(student -> {
                hr.setStudentName(student.getName());
            });
        }
        return helpRequests;
    }

    public List<HelpRequest> findByStudent(String id) {
        
        Query query = new Query(Criteria.where("student").is(id));
        return mongoTemplate.find(query, HelpRequest.class);
    }

    public boolean deleteIfStudentRemoved(String id){
        Query query = new Query(Criteria.where("student").is(id));
        DeleteResult result = mongoTemplate.remove(query, HelpRequest.class);
        return result.wasAcknowledged() && result.getDeletedCount() > 0;
    }

    public Map<String, Integer> helpRequestReport() {
    List<Student> students = studentRepository.findAll();
    Map<String, Integer> result = new HashMap<>();

    for (Student student : students) {
        // Consulta para contar solicitudes de ayuda del estudiante
        Query query = new Query(Criteria.where("student").is(student.getId()));
        long count = mongoTemplate.count(query, HelpRequest.class);

        // Guardar en el mapa: nombre como clave, cantidad como valor
        result.put(student.getName(), (int) count);
    }

    return result;
}


}
