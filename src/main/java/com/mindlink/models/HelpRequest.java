package com.mindlink.models;

import com.mindlink.Util.Enums.HelpRequestState;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "helpRequests")
public class HelpRequest implements Comparable<HelpRequest> {
    @Id
    private String id;
    private String student; //Id
    private String subject;
    private String type;
    private String body; 
    private String response; //Debería iniciar vacío
    private Integer priorityLevel;
    private HelpRequestState state;

    @Transient
    private String studentName; //Para mostrarlo al moderador


    @Override
    public int compareTo(HelpRequest o) {
        return this.priorityLevel.compareTo(o.priorityLevel); 
    }
}
