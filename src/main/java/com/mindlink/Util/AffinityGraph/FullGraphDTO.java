package com.mindlink.Util.AffinityGraph;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class FullGraphDTO {
    private List<Map<String, String>> nodes;
    private List<LinkDTO> links;

    public FullGraphDTO(List<String> nodeIds, List<LinkDTO> links) {
        this.nodes = nodeIds.stream()
            .map(id -> Map.of("id", id))
            .collect(Collectors.toList());
        this.links = links;
    }
}


