package com.mindlink.Util.AffinityGraph;

import com.mindlink.models.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
