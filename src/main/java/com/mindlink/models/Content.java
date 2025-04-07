package com.mindlink.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Content {
    private Integer id;
    private String title;
    private Student author;
    private String type;
    private String topic;
    // valoration's list (Optional)


}
