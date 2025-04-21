package com.mindlink.repositories;

import org.springframework.stereotype.Repository;

import com.mindlink.models.Valoration;

@Repository
public class ValorationRepository extends MongoRepositoryImpl<Valoration>{
    public ValorationRepository(){
        super(Valoration.class);
    }
}
