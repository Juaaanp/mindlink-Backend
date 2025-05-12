package com.mindlink.Util.AuxiliarClasses;

import java.util.Map;

import lombok.Data;

@Data
public class EmailRegisterRequest {
    
    private String subject;
    private String templateName;
    private Map<String, Object> dataTemplate;
    private String to;
}
