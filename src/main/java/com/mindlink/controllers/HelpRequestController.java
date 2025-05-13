package com.mindlink.controllers;

import com.mindlink.models.HelpRequest;
import com.mindlink.services.HelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/helpRequests")
public class HelpRequestController {

    @Autowired
    private HelpRequestService helpRequestService;

    @GetMapping("/findWithPriority")
    public List<HelpRequest> findWithPriority() {
        return helpRequestService.findAllPriority();
    }
    

    @GetMapping("/findByStudent/{id}")
    public List<HelpRequest> getHelpRequestByStudent(@PathVariable String id) {
        return helpRequestService.findByStudent(id);
    }


    // Crear una nueva solicitud de ayuda
    @PostMapping
    public ResponseEntity<HelpRequest> createHelpRequest(@RequestBody HelpRequest helpRequest) {
        HelpRequest savedHelpRequest = helpRequestService.save(helpRequest);
        return ResponseEntity.ok(savedHelpRequest);
    }

    // Obtener todas las solicitudes de ayuda
    @GetMapping
    public ResponseEntity<List<HelpRequest>> getAllHelpRequests() {
        List<HelpRequest> helpRequests = helpRequestService.findAll();
        return ResponseEntity.ok(helpRequests);
    }

    // Obtener una solicitud de ayuda por ID
    @GetMapping("/{id}")
    public ResponseEntity<HelpRequest> getHelpRequestById(@PathVariable String id) {
        Optional<HelpRequest> helpRequest = helpRequestService.findById(id);
        return helpRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una solicitud de ayuda
    @PutMapping("/{id}")
    public ResponseEntity<HelpRequest> updateHelpRequest(@PathVariable String id, @RequestBody HelpRequest helpRequest) {
        HelpRequest updatedHelpRequest = helpRequestService.update(id, helpRequest);
        return updatedHelpRequest != null ? ResponseEntity.ok(updatedHelpRequest) : ResponseEntity.notFound().build();
    }

    // Eliminar una solicitud de ayuda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHelpRequest(@PathVariable String id) {
        boolean deleted = helpRequestService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
