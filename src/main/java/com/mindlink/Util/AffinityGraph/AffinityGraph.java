package com.mindlink.Util.AffinityGraph;

import com.mindlink.models.Student;
import com.mindlink.models.StudyGroup;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Data
public class AffinityGraph {
    private static AffinityGraph instance;
    private Map<Student, List<Student>> graph;

    private AffinityGraph() {
        this.graph = new HashMap<>();
    }

    public static AffinityGraph getInstance() {
        if (instance == null) {
            instance = new AffinityGraph();
        }
        return instance;
    }

    public List<Student> getConnectionsForStudent(Student student) {
        return graph.getOrDefault(student, new ArrayList<>());
    }

    public void addConnection(Student student1, Student student2) {
        graph.computeIfAbsent(student1, k -> new ArrayList<>());
        if (!graph.get(student1).contains(student2)) {
            graph.get(student1).add(student2);
        }

        graph.computeIfAbsent(student2, k -> new ArrayList<>());
        if (!graph.get(student2).contains(student1)) {
            graph.get(student2).add(student1);
        }
    }

    public Map<Student, List<Student>> getGraph() {
        return this.graph;
    }

    // Método para exportar el grafo completo como DTO
    public FullGraphDTO getFullGraphDTO() {
        Set<Student> uniqueStudents = new HashSet<>();
        List<LinkDTO> links = new ArrayList<>();

        for (Map.Entry<Student, List<Student>> entry : graph.entrySet()) {
            Student source = entry.getKey();
            uniqueStudents.add(source);

            for (Student target : entry.getValue()) {
                uniqueStudents.add(target);

                // Para evitar duplicados en grafo no dirigido
                if (source.getId().compareTo(target.getId()) < 0) {
                    links.add(new LinkDTO(source.getId(), target.getId()));
                }
            }
        }

        return new FullGraphDTO(new ArrayList<>(uniqueStudents), links);
    }

    // Método para exportar el subgrafo como DTO

    public FullGraphDTO getSubGraphDTO(Student student) {
        List<Student> nodeList = new ArrayList<>();
        List<LinkDTO> links = new ArrayList<>();

        List<Student> connections = graph.getOrDefault(student, List.of());

        nodeList.add(student);
        for (Student other : connections) {
            nodeList.add(other);
            links.add(new LinkDTO(student.getId(), other.getId()));
        }

        return new FullGraphDTO(nodeList, links);
    }

    public void buildGraphFromStudyGroups(List<StudyGroup> studyGroups, Function<String, Student> findStudentById) {
        graph.clear(); // Limpiar el grafo antes de reconstruirlo

        for (StudyGroup group : studyGroups) {
            List<String> ids = group.getStudentIdList();
            if (ids == null)
                continue;

            for (int i = 0; i < ids.size(); i++) {
                for (int j = i + 1; j < ids.size(); j++) {
                    Student s1 = findStudentById.apply(ids.get(i));
                    Student s2 = findStudentById.apply(ids.get(j));

                    if (s1 != null && s2 != null) {
                        addConnection(s1, s2);
                    }
                }
            }
        }
    }

}
