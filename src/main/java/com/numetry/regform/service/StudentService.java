package com.numetry.regform.service;


import com.numetry.regform.entity.RegistrationStudent;
import com.numetry.regform.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public RegistrationStudent findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public String logoutStudent(String studentId, LocalDateTime logoutTime) {
        
        Optional<RegistrationStudent> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isPresent()) {
            RegistrationStudent student = optionalStudent.get();
         
            student.setLogoutTime(logoutTime);  
            studentRepository.save(student);

            return "Logout successful";
        } else {
          
            return "Student not found";
        }
    }
    
    public void save(RegistrationStudent student) {
        studentRepository.save(student);
    }
    
    public List<RegistrationStudent> findAllStudents() {
        return studentRepository.findAll();
    }

	public RegistrationStudent findById(String studentId) {
		// TODO Auto-generated method stub
		return null;
			
	}
	
	public String getUserType() {
	    return studentRepository.findById("your-admin-user-id").map(RegistrationStudent::getUserType).orElse(null);
	}

	public void updateLogoutTime(String studentId, LocalDateTime parsedLogoutTime) {
		// TODO Auto-generated method stub
		
	}
}

