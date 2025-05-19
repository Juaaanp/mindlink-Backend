package com.mindlink.Util.AffinityGraph;

import lombok.Data;

@Data
public class LinkDTO {
    private String source;
    private String target;

    public LinkDTO(String source, String target){
        this.source = source;
        this.target = target;
    }
}
