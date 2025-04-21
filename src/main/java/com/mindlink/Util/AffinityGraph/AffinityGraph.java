package com.mindlink.Util.AffinityGraph;

import com.mindlink.models.Student;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class AffinityGraph {
    private static AffinityGraph instance;
    private Map<Student, List<Student>> graph;

    private AffinityGraph() {
        this.graph = new HashMap<>();
    }

    public static AffinityGraph getInstance(){
        if (instance == null){
            instance = new AffinityGraph();
        }
        return instance;
    }
}
