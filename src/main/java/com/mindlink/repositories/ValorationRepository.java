package com.mindlink.repositories;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.mindlink.models.Valoration;

@Repository
public class ValorationRepository extends MongoRepositoryImpl<Valoration>{
    public ValorationRepository(){
        super(Valoration.class);
    }

    public List<Valoration> findByContentId(String contentId) {
        Query query = new Query(Criteria.where("content").is(contentId));
        return mongoTemplate.find(query, Valoration.class);
    }
}
