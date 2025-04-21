package com.mindlink.repositories;

import org.springframework.stereotype.Repository;

import com.mindlink.models.HelpRequest;

@Repository
public class HelpRequestRepository extends MongoRepositoryImpl<HelpRequest>{
    public HelpRequestRepository() {
        super(HelpRequest.class);
    }
}
