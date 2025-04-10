package com.mindlink.repositories;

import com.mindlink.models.StudyGroup;

public class StudyGroupRepository extends MongoRepositoryImpl<StudyGroup>{
    public StudyGroupRepository() {
        super(StudyGroup.class);
    }
}
