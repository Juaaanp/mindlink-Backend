package com.mindlink.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Valoration {
    private Integer id;
    private Student student;
    private Content content;
    private Byte rate;
    private String comment;

}
