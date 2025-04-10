package com.mindlink.repositories;

import com.mindlink.models.Valoration;

public class ValorationRepository extends MongoRepositoryImpl<Valoration>{
    public ValorationRepository(){
        super(Valoration.class);
    }
}
