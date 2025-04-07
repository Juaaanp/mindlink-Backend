package com.mindlink.repositories;

import com.mindlink.models.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository extends MongoRepositoryImpl<Student> {

    public StudentRepository() {
        super(Student.class);
    }
}
