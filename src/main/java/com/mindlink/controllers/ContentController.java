package com.mindlink.controllers;

import com.mindlink.models.Content;
import com.mindlink.models.Valoration;
import com.mindlink.services.ContentService;
import com.mindlink.services.ValorationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ValorationService valorationService;

    @GetMapping("/findByIdStudent/{id}")
    public List<Content> findByIdStudent(@PathVariable String id) {
        return contentService.findByIdStudent(id);
    }

    @GetMapping("/withAuthorName")
    public ResponseEntity<List<Content>> withAuthorName() {
        return ResponseEntity.ok(contentService.findWithAuthorName());
    }

    // Create a new content
    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        Content contentSaved = contentService.save(content);
        return ResponseEntity.ok(contentSaved);
    }

    // Get all contents
    @GetMapping
    public ResponseEntity<List<Content>> getAllContents() {
        List<Content> contents = contentService.findAll();
        return ResponseEntity.ok(contents);
    }

    // Get a content by id
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable String id) {
        Optional<Content> content = contentService.findById(id);
        return content.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all contents with the same title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Content>> getContentsByTitle(@PathVariable String title) {
        List<Content> contentsFound = contentService.findByTitle(title);
        return ResponseEntity.ok(contentsFound);
    }

    // Get all contents with the same author
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Content>> getContentsByAuthor(@PathVariable String author) {
        List<Content> contentsFound = contentService.findByAuthor(author);
        return ResponseEntity.ok(contentsFound);
    }

    // Get all contents with the same type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Content>> getContentsByType(@PathVariable String type) {
        List<Content> contentsFound = contentService.findByType(type);
        return ResponseEntity.ok(contentsFound);
    }

    // Update a content by id
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable String id, @RequestBody Content content) {
        Content updatedContent = contentService.update(id, content);
        return updatedContent != null ? ResponseEntity.ok(updatedContent) : ResponseEntity.notFound().build();
    }

    // Delete a content by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable String id) {
        boolean deleted = contentService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Obtener los contenidos mas valorados
    @GetMapping("/highValorations")
    public ResponseEntity<List<Content>> getHighValorationsContents() {
        List<Valoration> highValorations = valorationService.highValorations();
        List<Content> contents = contentService.findAll();
        List<Content> highContents = new ArrayList<>();

        // Extrae los IDs de contenido de las valoraciones altas
        Set<String> highContentIds = highValorations.stream().map(Valoration::getContent).collect(Collectors.toSet());

        // Añade los contenidos cuyo ID esté en ese set
        for (Content content : contents) {
            if (highContentIds.contains(content.getId())) {
                highContents.add(content);
            }
        }

        return ResponseEntity.ok(highContents);
    }

}
