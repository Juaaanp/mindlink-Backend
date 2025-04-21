package com.mindlink.services;

import com.mindlink.models.Content;
import com.mindlink.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public Content save(Content content){
        return contentRepository.save(content);
    }

    public List<Content> findAll(){
        return contentRepository.findAll();
    }

    public Optional<Content> findById(String id){
        return contentRepository.findById(id);
    }

    public List<Content> findByTopic(String topic){
        return contentRepository.findByTopic(topic);
    }

    public List<Content> findByAuthor(String author){
        return contentRepository.findByAuthor(author);
    }

    public List<Content> findByType(String type){
        return contentRepository.findByType(type);
    }

    public Content update (String id, Content contentUpdated){
        if (contentRepository.findById(id).isPresent()){
            contentUpdated.setId(id);
            return contentRepository.save(contentUpdated);
        }
        return null;
    }

    //Posible opción:
    // public Content update(String id, Content newContentData) {
    //     return contentRepository.findById(id).map(existing -> {
    //         existing.setTitle(newContentData.getTitle());
    //         existing.setAuthor(newContentData.getAuthor());
    //         existing.setType(newContentData.getType());
    //         existing.setTopic(newContentData.getTopic());
    //         return contentRepository.save(existing);
    //     }).orElse(null);
    // }

    public boolean delete (String id){
        if (contentRepository.findById(id).isPresent()){
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
