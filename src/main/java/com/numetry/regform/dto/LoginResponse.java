package com.numetry.regform.dto;
import java.time.LocalDateTime;

import com.numetry.regform.entity.RegistrationStudent;

import lombok.Data;

@Data
public class LoginResponse {
    private String id;
    private String fullName;
    private String username;
    private String email;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private String userType;
    private String message;

    // Default constructor
    public LoginResponse() {
    }

    // Constructor RegistrationStudent param
    public LoginResponse(RegistrationStudent student) {
        this.id = student.getId();
        this.fullName = student.getFullName();
        this.username = student.getUsername();
        this.email = student.getEmail();
        this.userType = student.getUserType();
        this.loginTime = student.getLoginTime();
        this.logoutTime = student.getLogoutTime();
    }

   
}
