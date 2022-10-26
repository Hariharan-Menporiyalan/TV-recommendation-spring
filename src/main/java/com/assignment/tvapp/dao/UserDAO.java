package com.assignment.tvapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.assignment.tvapp.entity.NewUser;

public interface UserDAO extends JpaRepository<NewUser, Long> {
	
	@Query("select name from NewUser where email = :email")
	public String getUserName(String email);
	
	@Modifying
	@Query("delete from NewUser where email=:email and showId is not null")
	public void deleteRecommendationHistory(String email);
	
	@Query("select index from NewUser where email = :email and showId is null")
	public Long getUserIndex(String email);
	
	@Query("select showId from NewUser where email = :email and showId is not null")
	public List<Integer> getshowIdList(String email);
}
