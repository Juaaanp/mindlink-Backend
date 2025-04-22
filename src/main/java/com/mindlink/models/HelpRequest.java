package com.mindlink.models;

import com.mindlink.Util.Enums.HelpRequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "helpRequests")
public class HelpRequest implements Comparable<HelpRequest> {
    @Id
    private String id;
    private String student; //Ahora se referencia con id
    private String topic;
    private Integer priorityLevel;
    private HelpRequestState state;


    @Override
    public int compareTo(HelpRequest o) {
        return this.priorityLevel.compareTo(o.priorityLevel); 
    }
}
