package com.mindlink.services;

import com.mindlink.Util.AffinityGraph.AffinityGraph;
import com.mindlink.Util.AffinityGraph.FullGraphDTO;
import com.mindlink.Util.AuxiliarClasses.StudentDTO;
import com.mindlink.Util.AuxiliarClasses.StudentGraphDTO;
import com.mindlink.exceptions.InvalidCredentialsException;
import com.mindlink.exceptions.InvalidEmailException;
import com.mindlink.models.Student;
import com.mindlink.repositories.StudentRepository;
import com.mindlink.repositories.StudyGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    public void registerStudyGroupsForStudent(Student student) {
        studyGroupRepository.registerStudyGroupsForStudent(student);
    }

    public Student loadStudentAndContents(String id) {
        return studentRepository.cargarEstudianteConContenidos(id);
    }

    public StudentDTO existsByEmailAndPassword(String email, String password) {
        Student student = studentRepository.findByEmailAndPassword(email, password);
        if (student == null) {
            throw new InvalidCredentialsException();
        }
        return new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getInterests());
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

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // obtener el grafo completo (moderador)
    public FullGraphDTO getFullGraphForModerator() {
        return AffinityGraph.getInstance().getFullGraphDTO();
    }

    // obtener el subgrafo (estudiante)
    public StudentGraphDTO getSubgraphForStudent(String studentId) {
    Optional<Student> optionalStudent = findById(studentId);
    if (optionalStudent.isEmpty()) throw new RuntimeException("Student not found");

    Student student = optionalStudent.get();
    List<Student> connections = AffinityGraph.getInstance().getConnectionsForStudent(student);
    List<String> connectedIds = connections.stream().map(Student::getId).collect(Collectors.toList());

    return new StudentGraphDTO(studentId, connectedIds);
}


}
