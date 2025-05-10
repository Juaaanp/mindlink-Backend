package com.mindlink.Util.AuxiliarClasses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentGraphDTO {
    private String studentId;
    private List<String> connections;

}
