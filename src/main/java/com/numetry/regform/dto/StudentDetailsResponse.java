package com.numetry.regform.dto;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class StudentDetailsResponse {
    private String id;
    private String fullName;
    private String username;
    private String email;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

}
