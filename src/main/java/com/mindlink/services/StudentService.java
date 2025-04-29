package com.mindlink.services;

import com.mindlink.exceptions.InvalidCredentialsException;
import com.mindlink.exceptions.InvalidEmailException;
import com.mindlink.models.Student;
import com.mindlink.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student loadStudentAndContents(String id) {
        return studentRepository.cargarEstudianteConContenidos(id);
    }

    public boolean existsByEmailAndPassword(String email, String password) {
        if (!studentRepository.existsByEmailAndPassword(email, password)) {
            throw new InvalidCredentialsException();
        }
        return true;
    }

    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    public Student save(Student student) {
        if (existsByEmail(student.getEmail())) {
            throw new InvalidEmailException();
        }
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    public Student update(String id, Student updatedStudent) {
        if (studentRepository.findById(id).isPresent()) {
            updatedStudent.setId(id);
            return studentRepository.save(updatedStudent);
        }
        return null;
    }

    public boolean delete(String id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
