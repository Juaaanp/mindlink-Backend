package com.mindlink.models;

import com.mindlink.Util.Enums.HelpRequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "helpRequests")
public class HelpRequest {
    @Id
    private String id;
    private Student student;
    private String topic;
    private Integer priorityLevel;
    private HelpRequestState state;

}
