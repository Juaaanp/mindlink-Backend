package com.mindlink.repositories;

import com.mindlink.models.HelpRequest;

public class HelpRequestRepository extends MongoRepositoryImpl<HelpRequest>{
    public HelpRequestRepository() {
        super(HelpRequest.class);
    }
}
