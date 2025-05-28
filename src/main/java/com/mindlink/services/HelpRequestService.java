package com.mindlink.services;

import com.mindlink.Util.PriorityQueue.PriorityQueue;
import com.mindlink.models.HelpRequest;
import com.mindlink.repositories.HelpRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HelpRequestService {

    @Autowired
    private HelpRequestRepository helpRequestRepository;

    public List<HelpRequest> findAllPriority() {

        List<HelpRequest> finalReqs = new ArrayList<>();
        List<HelpRequest> reqs = helpRequestRepository.findAllWithStudentName();
        PriorityQueue<HelpRequest> queueReq = new PriorityQueue<>();

        for (HelpRequest req: reqs) { //1. Insertamos la list a la cola para organizar por prioridad (mayor a menor)
            queueReq.insert(req);
        }

        while (!queueReq.isEmpty()) { //2. Insertamos la cola en una lista para poder mandarlo al cliente
            finalReqs.add(queueReq.poll()); //Poll() devolvería la lista desde lo mayor (primer nodo) a lo menor
        }
        return finalReqs;
    }

    public List<HelpRequest> findByStudent(String id) {
        return helpRequestRepository.findByStudent(id);
    }

    public HelpRequest save(HelpRequest helpRequest) {
        return helpRequestRepository.save(helpRequest);
    }

    public List<HelpRequest> findAll() {
        return helpRequestRepository.findAll();
    }

    public Optional<HelpRequest> findById(String id) {
        return helpRequestRepository.findById(id);
    }

    public HelpRequest update(String id, HelpRequest updatedHelpRequest) {
        if (helpRequestRepository.findById(id).isPresent()) {
            updatedHelpRequest.setId(id);
            return helpRequestRepository.save(updatedHelpRequest);
        }
        return null;
    }

    public boolean delete(String id) {
        if (helpRequestRepository.findById(id).isPresent()) {
            helpRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteIfStudentRemoved (String id){
        return helpRequestRepository.deleteIfStudentRemoved(id);
    }

    public Map<String,Integer> helpRequestReport(){
        return helpRequestRepository.helpRequestReport();
    }


}
