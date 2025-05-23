package com.mindlink.repositories;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.mindlink.models.Valoration;
import com.mongodb.client.result.DeleteResult;

@Repository
public class ValorationRepository extends MongoRepositoryImpl<Valoration>{
    public ValorationRepository(){
        super(Valoration.class);
    }

    public List<Valoration> findByContentId(String contentId) {
        Query query = new Query(Criteria.where("content").is(contentId));
        return mongoTemplate.find(query, Valoration.class);
    }

    // Borrar una o mas valoraciones que coincidan con el id (creadas por él) de el estudiante eliminado.
    public boolean deleteIfStudentRemoved (String id){
        Query query = new Query(Criteria.where("student").is(id));
        DeleteResult result = mongoTemplate.remove(query, Valoration.class);
        return result.wasAcknowledged() && result.getDeletedCount() > 0;
    }

    // Borrar valoracion perteneciente a contenido eliminado
    public boolean deleteIfContentRemoved (String id){
        Query query = new Query(Criteria.where("content").is(id));
        DeleteResult result = mongoTemplate.remove(query, Valoration.class);
        return result.wasAcknowledged() && result.getDeletedCount() > 0;
    }
}
