package com.mindlink.controllers;

import com.mindlink.models.Moderator;
import com.mindlink.services.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moderators")
public class ModeratorController {

    @Autowired
    private ModeratorService moderatorService;

    // Crear un nuevo moderador
    @PostMapping
    public ResponseEntity<Moderator> createModerator(@RequestBody Moderator moderator) {
        Moderator savedModerator = moderatorService.save(moderator);
        return ResponseEntity.ok(savedModerator);
    }

    // Obtener todos los moderadores
    @GetMapping
    public ResponseEntity<List<Moderator>> getAllModerators() {
        List<Moderator> moderators = moderatorService.findAll();
        return ResponseEntity.ok(moderators);
    }

    // Obtener un moderador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Moderator> getModeratorById(@PathVariable String id) {
        Optional<Moderator> moderator = moderatorService.findById(id);
        return moderator.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un moderador
    @PutMapping("/{id}")
    public ResponseEntity<Moderator> updateModerator(@PathVariable String id, @RequestBody Moderator moderator) {
        Moderator updatedModerator = moderatorService.update(id, moderator);
        return updatedModerator != null ? ResponseEntity.ok(updatedModerator) : ResponseEntity.notFound().build();
    }

    // Eliminar un moderador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModerator(@PathVariable String id) {
        boolean deleted = moderatorService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
