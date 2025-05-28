package com.mindlink.Util.AffinityGraph;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mindlink.models.Student;

import lombok.Data;

@Data
public class FullGraphDTO {
    private List<Map<String, String>> nodes; // Sigue siendo válido

    private List<LinkDTO> links;

    public FullGraphDTO(List<Student> students, List<LinkDTO> links) {
        this.nodes = students.stream()
            .map(student -> Map.of(
                "id", student.getId(),
                "name", student.getName()
            ))
            .collect(Collectors.toList());
        this.links = links;
    }
}



