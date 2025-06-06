package com.mindlink.repositories;

import com.mindlink.Util.BinarySearchTree.BinarySearchTree;
import com.mindlink.models.Content;
import com.mindlink.services.ValorationService;
import com.mongodb.client.result.DeleteResult;

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

    @Autowired
    private ValorationService valorationService;

    public List<Content> findByIdStudent(String id) {

        Query query = new Query(Criteria.where("authorId").is(id));
        return mongoTemplate.find(query, Content.class);
    }

    public List<Content> findWithAuthorName() {
        List<Content> contents = mongoTemplate.findAll(Content.class);
        BinarySearchTree<Content> bst = new BinarySearchTree<>();

        for (Content content : contents) {
            // Buscar el autor por ID
            studentRepository.findById(content.getAuthorId()).ifPresent(author -> {
                content.setAuthorName(author.getName());
            });
            bst.insert(content);
        }
        return bst.toSortedList();
        // return contents;
    }

    // Cambio de los 3 metodos de filtrado para que funcionen con la logica de un
    // contains y no de un equals para efectos de practicidad al usuario.
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

    // Borrar uno o mas contenidos que coincidan con el id de el estudiante
    // eliminado.
    public boolean deleteIfStudentRemoved(String studentId) {
    // Buscar contenidos creados por el estudiante
    Query query = new Query(Criteria.where("authorId").is(studentId));
    List<Content> contentsToDelete = mongoTemplate.find(query, Content.class);

    if (contentsToDelete.isEmpty()) return false;

    // Eliminar las valoraciones asociadas a cada contenido
    for (Content content : contentsToDelete) {
        valorationService.deleteIfContentRemoved(content.getId());
    }

    // Eliminar los contenidos
    DeleteResult result = mongoTemplate.remove(query, Content.class);
    return result.wasAcknowledged() && result.getDeletedCount() > 0;
}


    

}
