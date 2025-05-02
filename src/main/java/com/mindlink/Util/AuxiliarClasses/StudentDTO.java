package com.mindlink.Util.AuxiliarClasses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO {
    private String id;
    private String name;
    private String email;
    private List<String> interests;
}
