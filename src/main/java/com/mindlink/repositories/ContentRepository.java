package com.mindlink.repositories;

import com.mindlink.models.Content;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentRepository extends MongoRepositoryImpl<Content>{
    public ContentRepository() {
        super(Content.class);
    }

    public List<Content> findByTopic(String topic){
        Query query = new Query();
        query.addCriteria(Criteria.where("topic").is(topic));
        return mongoTemplate.find(query, Content.class);
    }

    public List<Content> findByAuthor(String author){
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(author));
        return mongoTemplate.find(query, Content.class);
    }

    public List<Content> findByType(String type){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(type));
        return mongoTemplate.find(query, Content.class);
    }
}
