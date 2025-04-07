package com.mindlink.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

public abstract class MongoRepositoryImpl<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    private final Class<T> typeEntity;

    public MongoRepositoryImpl(Class<T> entity) {
        this.typeEntity = entity;
    }

    public T save(T entity) {
        return mongoTemplate.save(entity);
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(typeEntity);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, typeEntity));
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, typeEntity);
    }
}
