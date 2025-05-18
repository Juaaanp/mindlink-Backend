package com.mindlink.repositories;

import com.mindlink.models.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository // Es necesario para la inyección de dependencias con @Autowired
public class ContentRepository extends MongoRepositoryImpl<Content> {
    public ContentRepository() {
        super(Content.class);
    }

    @Autowired
    private StudentRepository studentRepository;

    public List<Content> findByIdStudent(String id) {

        Query query = new Query(Criteria.where("authorId").is(id));
        return mongoTemplate.find(query, Content.class);
    }

    public List<Content> findWithAuthorName() {
        List<Content> contents = mongoTemplate.findAll(Content.class);

        for (Content content : contents) {
            // Buscar el autor por ID
            studentRepository.findById(content.getAuthorId()).ifPresent(author -> {
                content.setAuthorName(author.getName());
            });
        }
        return contents;
    }

    // Cambio de los 3 metodos de filtrado para que funcionen con la logica de un contains y no de un equals para efectos de practicidad al usuario.
    public List<Content> findByTitle(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(".*" + Pattern.quote(title) + ".*", "i"));
        return mongoTemplate.find(query, Content.class);
    }

    public List<Content> findByAuthor(String author) {
        Query query = new Query();
        query.addCriteria(Criteria.where("author").regex(".*" + Pattern.quote(author) + ".*", "i"));
        return mongoTemplate.find(query, Content.class);
    }

    public List<Content> findByType(String type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").regex(".*" + Pattern.quote(type) + ".*", "i"));
        return mongoTemplate.find(query, Content.class);
    }

}
