package com.mindlink.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mindlink.Util.AuxiliarClasses.StudyGroupDTO;
import com.mindlink.Util.SimplyLinkedList.SimplyLinkedList;
import com.mindlink.models.Student;
import com.mindlink.models.StudyGroup;
import com.mongodb.client.result.UpdateResult;

@Repository
public class StudyGroupRepository extends MongoRepositoryImpl<StudyGroup> {
    public StudyGroupRepository() {
        super(StudyGroup.class);
    }

    public void addDescriptionToGroup(String groupId, String newDescription) {
        Query query = new Query(Criteria.where("id").is(groupId));
        Update update = new Update().set("description", newDescription);
        mongoTemplate.updateFirst(query, update, StudyGroup.class);

    }

    public void addContentToGroup(String groupId, String newContent) {
        Query query = new Query(Criteria.where("id").is(groupId));
        Update update = new Update().push("contents", newContent);
        mongoTemplate.updateFirst(query, update, StudyGroup.class);
    }

    
    public List<StudyGroupDTO> findGroupsByStudent(String studentId) {

        Query groupQuery = new Query(Criteria.where("studentIdList").in(studentId)); // Buscar los grupos que contienen
                                                                                     // al estudiante
        List<StudyGroup> studyGroups = mongoTemplate.find(groupQuery, StudyGroup.class);

        List<StudyGroupDTO> dtos = new ArrayList<>();

        for (StudyGroup group : studyGroups) { //Seteo el DTO de cada grupo de estudio
            StudyGroupDTO dto = new StudyGroupDTO();
            dto.setId(group.getId());
            dto.setTopic(group.getTopic());
            dto.setDescription(group.getDescription());
            dto.setContents(group.getContents());

            //Obtengo los estudiantes del grupo de estudio con sus ids
            List<String> studentIds = group.getStudentIdList();

            Query studentQuery = new Query(Criteria.where("id").in(studentIds));
            List<Student> students = mongoTemplate.find(studentQuery, Student.class);

            //Mapear students a DTOs
            List<StudyGroupDTO.Student> studentDTOs = students.stream().map(student -> {
                StudyGroupDTO.Student dtoStudent = new StudyGroupDTO.Student();
                dtoStudent.setId(student.getId());
                dtoStudent.setName(student.getName());
                dtoStudent.setEmail(student.getEmail());
                return dtoStudent;
            }).collect(Collectors.toList());

            dto.setStudents(studentDTOs); //StudyGroupDTO
            dtos.add(dto); //Lista de los StudyGroupDTO del estudiante
        }

        return dtos; //Aca puedo meterlos en una lista enlazada y luego sacarlos para cumplir con los requisitos
    }

    public List<StudyGroupDTO> getAllGroupDTOs() { //Versión optimizada de consultas. Incluso carga más rápido

        List<StudyGroup> groups = findAll();
        List<StudyGroupDTO> groupDTOs = new ArrayList<>();

        // Paso 1: Recolectar todos los IDs de estudiantes (sin duplicados)
        Set<String> allStudentIds = groups.stream()
            .flatMap(group -> group.getStudentIdList().stream())
            .collect(Collectors.toSet());

        // Paso 2: Hacer UNA sola consulta a Mongo
        Query studentQuery = new Query(Criteria.where("id").in(allStudentIds));
        List<Student> allStudents = mongoTemplate.find(studentQuery, Student.class);

        // Paso 3: Crear un mapa id -> Student
        Map<String, Student> studentMap = allStudents.stream()
            .collect(Collectors.toMap(Student::getId, student -> student));

        // Paso 4: Mapear grupos a DTOs
        for (StudyGroup g : groups) {
            StudyGroupDTO dto = new StudyGroupDTO();
            dto.setId(g.getId());
            dto.setTopic(g.getTopic());
            dto.setDescription(g.getDescription());
            dto.setContents(g.getContents());

            // Mapear los estudiantes de este grupo usando el mapa
            List<StudyGroupDTO.Student> studentDTOs = g.getStudentIdList().stream()
                .map(studentId -> {
                    Student student = studentMap.get(studentId);
                    if (student == null) return null; // Por si falta alguno
                    StudyGroupDTO.Student dtoStudent = new StudyGroupDTO.Student();
                    dtoStudent.setId(student.getId());
                    dtoStudent.setName(student.getName());
                    dtoStudent.setEmail(student.getEmail());
                    return dtoStudent;
                })
                .filter(Objects::nonNull) // Remueve los que no se encontraron
                .collect(Collectors.toList());

            dto.setStudents(studentDTOs);
            groupDTOs.add(dto);
        }

        return groupDTOs;
    }


    public void registerStudyGroupsForStudent(Student student) { // Falta poner el id del grupo al estudiante?

        List<StudyGroup> studyGroups = findAll();

        // Mapeo de topic a grupo de estudio para búsqueda rápida
        Map<String, StudyGroup> topicToGroupMap = new HashMap<>();
        for (StudyGroup sg : studyGroups) {
            topicToGroupMap.put(sg.getTopic(), sg);
        }

        // Recorrer intereses del estudiante
        for (String interest : student.getInterests()) {
            StudyGroup group = topicToGroupMap.get(interest);

            if (group != null) {
                // Si ya existe un grupo para ese interés, agregar al estudiante
                if (!group.getStudentIdList().contains(student.getId())) {
                    group.getStudentIdList().add(student.getId());
                    save(group);
                }
            } else {
                // Si no existe un grupo, crearlo y agregar al estudiante
                StudyGroup newGroup = new StudyGroup();
                newGroup.setTopic(interest);
                newGroup.setStudentIdList(new ArrayList<>(List.of(student.getId())));
                save(newGroup);
            }
        }
    }

    public List<StudyGroup> cargarTodosGruposConEstudiantes() {
        List<StudyGroup> grupos = mongoTemplate.findAll(StudyGroup.class);

        for (StudyGroup grupo : grupos) {
            if (grupo.getStudentIdList() != null && !grupo.getStudentIdList().isEmpty()) {
                Query query = new Query(Criteria.where("_id").in(grupo.getStudentIdList()));
                List<Student> estudiantes = mongoTemplate.find(query, Student.class);

                // Crear lista propia
                SimplyLinkedList<Student> listaPropia = new SimplyLinkedList<>();
                for (Student estudiante : estudiantes) {
                    listaPropia.addLast(estudiante);
                }

                grupo.setStudentOwnList(listaPropia);
            }
        }
        return grupos;
    }

    // Borrar el id del estudiante eliminado de los grupos de estudio a los cuales pertenecía.
    public boolean deleteStudentFromGroups(String studentId) {
    Query query = new Query(Criteria.where("studentIdList").in(studentId));
    Update update = new Update().pull("studentIdList", studentId);
    UpdateResult result = mongoTemplate.updateMulti(query, update, StudyGroup.class);
    return result.wasAcknowledged();
}
}
