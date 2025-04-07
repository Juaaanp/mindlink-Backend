package com.mindlink.models;

import com.mindlink.Util.Enums.HelpRequestState;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelpRequest {
    private Integer id;
    private Student student;
    private String topic;
    private Integer priorityLevel;
    private HelpRequestState state;

}
