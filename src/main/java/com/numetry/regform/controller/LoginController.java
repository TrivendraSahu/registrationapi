package com.numetry.regform.controller;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numetry.regform.dto.LoginRequest;
import com.numetry.regform.dto.LoginResponse;
import com.numetry.regform.entity.RegistrationStudent;
import com.numetry.regform.service.StudentService; // Import StudentService

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/students")
public class LoginController {

  

    @Autowired
    private StudentService studentService;


    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String userType = loginRequest.getUserType();

      
        RegistrationStudent student = studentService.findByUsername(username);
        if (student != null && student.getPassword().equals(password)) {
          
            student.setLoginTime(LocalDateTime.now());

           
            studentService.save(student);

           
            LoginResponse response = new LoginResponse();
            response.setFullName(student.getFullName());
            response.setUsername(student.getUsername());
            response.setEmail(student.getEmail());
            response.setLoginTime(student.getLoginTime());
            response.setUserType(student.getUserType());

           
            if (student.getUserType().equalsIgnoreCase(userType)) {
                response.setMessage("Login successful");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user type");
            }
        } else {
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    
//for logout
    @PostMapping("/logout/{studentId}/{logoutTime}")
    public ResponseEntity<String> logoutStudent(@PathVariable String studentId, @PathVariable String logoutTime) {
        // Fetch the student from the database based on the provided studentId
        RegistrationStudent student = studentService.findById(studentId);

        if (student != null) {
            try {
               
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                LocalDateTime parsedLogoutTime = LocalDateTime.parse(logoutTime, formatter);

               
                student.setLogoutTime(parsedLogoutTime);

                
                studentService.updateLogoutTime(studentId, parsedLogoutTime);

                System.out.println("Logout successful");

               
                return ResponseEntity.ok("Logout successful");
            } catch (DateTimeParseException e) {
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid logout time format");
            }
        } else {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }


//to get all details
    @GetMapping("/users")
    public ResponseEntity<List<LoginResponse>> getAllUsers() {
        List<LoginResponse> userResponses = new ArrayList<>();
        List<RegistrationStudent> students = studentService.findAllStudents();

        Function<RegistrationStudent, LoginResponse> mapToLoginResponse = student -> {
            LoginResponse response = new LoginResponse();
            response.setId(student.getId());
            response.setFullName(student.getFullName());
            response.setUsername(student.getUsername());
            response.setEmail(student.getEmail());
            response.setUserType(student.getUserType());
            response.setLoginTime(student.getLoginTime());
            response.setLogoutTime(student.getLogoutTime());
            return response;
        };

        if (students != null && !students.isEmpty()) {
            userResponses = students.stream()
                                    .map(mapToLoginResponse)
                                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(userResponses);
    }
 }


   



