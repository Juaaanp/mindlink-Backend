package com.mindlink.repositories;

import com.mindlink.models.Moderator;

public class ModeratorRepository extends MongoRepositoryImpl<Moderator>{
    public ModeratorRepository(){
        super(Moderator.class);
    }
}
