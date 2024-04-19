package com.numetry.regform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numetry.regform.entity.RegistrationStudent;

@Repository
public interface StudentRepository extends JpaRepository<RegistrationStudent,String>{

     Optional<RegistrationStudent> findFirstByUsername(String username);
     RegistrationStudent findByUsername(String username);
     
     boolean existsByUsername(String username); 

	RegistrationStudent findByUsernameAndPassword(String username, String password);
    


	
	
	

}



