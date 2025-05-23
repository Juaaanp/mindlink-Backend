package com.mindlink.controllers;

import com.mindlink.models.Valoration;
import com.mindlink.services.ValorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/valorations")
public class ValorationController {

    @Autowired
    private ValorationService valorationService;

    @GetMapping("/byContent/{contentId}")
    public List<Valoration> findByContent(@PathVariable String contentId) {
        return valorationService.findByContent(contentId);
    }
    

    // Create a new valoration
    @PostMapping
    public ResponseEntity<Valoration> createValoration(@RequestBody Valoration valoration) {
        Valoration savedValoration = valorationService.save(valoration);
        return ResponseEntity.ok(savedValoration);
    }

    // Get all valorations
    @GetMapping
    public ResponseEntity<List<Valoration>> getAllValorations() {
        List<Valoration> valorations = valorationService.findAll();
        return ResponseEntity.ok(valorations);
    }

    // Get a valoration by id
    @GetMapping("/{id}")
    public ResponseEntity<Valoration> getValorationById(@PathVariable String id) {
        Optional<Valoration> valoration = valorationService.findById(id);
        return valoration.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a valoration by id
    @PutMapping("/{id}")
    public ResponseEntity<Valoration> updateValoration(@PathVariable String id, @RequestBody Valoration valoration) {
        Valoration updatedValoration = valorationService.update(id, valoration);
        return updatedValoration != null ? ResponseEntity.ok(updatedValoration) : ResponseEntity.notFound().build();
    }

    // Delete a valoration by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValoration(@PathVariable String id) {
        boolean deleted = valorationService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/high")
    public ResponseEntity<List<Valoration>> getHighValorations(){
        List<Valoration> highValorations = valorationService.highValorations();
        return ResponseEntity.ok(highValorations);
    }
}
