package com.mindlink.Util.AuxiliarClasses;

import java.util.List;

import lombok.Data;

@Data
public class StudyGroupDTO {
    
    private String id;
    private String topic;
    private String description;
    private List<Student> students;
    private List<String> contents;

    @Data
    public static class Student {
        private String id;
        private String name;
        private String email;
    }
}
