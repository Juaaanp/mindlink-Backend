package com.mindlink.repositories;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mindlink.models.HelpRequest;

@Repository
public class HelpRequestRepository extends MongoRepositoryImpl<HelpRequest>{
    public HelpRequestRepository() {
        super(HelpRequest.class);
    }

    public List<HelpRequest> findByStudent(String id) {
        
        Query query = new Query(Criteria.where("student").is(id));
        return mongoTemplate.find(query, HelpRequest.class);
    }
}
