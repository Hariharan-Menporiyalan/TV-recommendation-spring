package com.assignment.tvapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.tvapp.entity.NewUser;

public interface UserDAO extends JpaRepository<NewUser, String> {
	
	@Query("select name from NewUser where email = :email")
	String getUserName(String email);

}
