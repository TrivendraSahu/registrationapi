package com.numetry.regform.entity;




import java.security.SecureRandom;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "student_regform")
public class RegistrationStudent {

   @Id
    @Column(unique = true)
    private String id; // Define ID field

    @Size(min=5,max=25,message="fullname must be 5 to 25 characters")
    private String fullName;

    @NotBlank
    @Size(min=2, max=15,message=" username must be 2 to 15 characters")
    private String username;

    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;
    

    private LocalDateTime loginTime;
    
    private LocalDateTime logoutTime; 
    private String userType;
    
  
   

    public RegistrationStudent(String fullName, String username, String email, String password, String userType) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = generateRandomId();
        this.userType = userType != null ? userType : ""; 
    }

        
        @PrePersist
        public void prePersist() {
            if (this.id == null) {
                this.id = generateRandomId();
            }
    }
    

    private String generateRandomId() {
        int length = 8; 
        String characters = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
    public void setLoginTime() {
        this.loginTime = LocalDateTime.now();
    }
    
    public void setLogoutTime() {
        this.logoutTime = LocalDateTime.now();
    }
    

}

