package com.mindlink.repositories;

import org.springframework.stereotype.Repository;

import com.mindlink.models.Moderator;

@Repository
public class ModeratorRepository extends MongoRepositoryImpl<Moderator>{
    public ModeratorRepository(){
        super(Moderator.class);
    }
}
