package com.mindlink.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mindlink.models.HelpRequest;
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
}
