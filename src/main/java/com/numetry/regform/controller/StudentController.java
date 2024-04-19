package com.numetry.regform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numetry.regform.entity.RegistrationStudent;
import com.numetry.regform.repository.StudentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create operation
    @PostMapping("/register")
    public RegistrationStudent createStudent(@RequestBody RegistrationStudent formData) {
        System.out.println("Received registration request: " + formData);
        return studentRepository.save(formData);
    }

    // Read operation - Get all students
    @GetMapping("/all")
    public List<RegistrationStudent> getAllStudents() {
        return studentRepository.findAll();
    }

    // Read operation - Get student by ID
    @GetMapping("/{id}")
    public Optional<RegistrationStudent> getStudentById(@PathVariable String id) {
        return studentRepository.findById(id);
    }

    // Update operation
    @PutMapping("/update/{id}")
    public RegistrationStudent updateStudent(@PathVariable String id, @RequestBody RegistrationStudent studentDetails) {
        Optional<RegistrationStudent> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            RegistrationStudent student = optionalStudent.get();
            student.setFullName(studentDetails.getFullName());
            student.setUsername(studentDetails.getUsername());
            student.setEmail(studentDetails.getEmail());
            student.setPassword(studentDetails.getPassword());
            student.setUserType(studentDetails.getUserType());
            return studentRepository.save(student);
        } else {
            return null; 
        }
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return new ResponseEntity<>("Student with ID: " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    // Delete all students
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllStudents() {
        studentRepository.deleteAll();
        return new ResponseEntity<>("All students deleted successfully", HttpStatus.OK);
    }
}

