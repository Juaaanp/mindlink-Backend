package com.mindlink.repositories;

import org.springframework.stereotype.Repository;

import com.mindlink.models.StudyGroup;

@Repository
public class StudyGroupRepository extends MongoRepositoryImpl<StudyGroup>{
    public StudyGroupRepository() {
        super(StudyGroup.class);
    }
}
