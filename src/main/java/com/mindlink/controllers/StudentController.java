package com.mindlink.controllers;

import com.mindlink.Util.AuxiliarClasses.LoginRequest;
import com.mindlink.Util.AuxiliarClasses.StudentDTO;
import com.mindlink.exceptions.NoLoggedUserException;
import com.mindlink.models.Student;
import com.mindlink.services.StudentService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //Get the student + his contents in an own list
    @GetMapping("/studentContent/{id}")
    public Student getStudentsAndContents(@PathVariable String id) {
        return studentService.loadStudentAndContents(id);
    }

    //Obtener usuario logueado
    @GetMapping("/me")
    public ResponseEntity<StudentDTO> getLoggedUser(HttpSession session) {
        StudentDTO student = (StudentDTO) session.getAttribute("student");
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            throw new NoLoggedUserException(); //Si no mando cookie la sesión se devuelve vacía
        }
    }
    
    //Login
    @PostMapping("login")
    public StudentDTO login(@RequestBody LoginRequest loginReq, HttpSession httpSession) {
        StudentDTO studentDTO = studentService.existsByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        httpSession.setAttribute("student", studentDTO); //guarda el DTO del logged user actual para ser accedido cuando quiera
        return studentDTO;
    }
    

    //Register, se valida el email en el service
    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return ResponseEntity.ok(savedStudent);
    }
    


    // Crear un nuevo estudiante, sobraría con register, BORRAR
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    // Obtener todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Optional<Student> student = studentService.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un estudiante
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        Student updatedStudent = studentService.update(id, student);
        return updatedStudent != null ? ResponseEntity.ok(updatedStudent) : ResponseEntity.notFound().build();
    }

    // Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        boolean deleted = studentService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}