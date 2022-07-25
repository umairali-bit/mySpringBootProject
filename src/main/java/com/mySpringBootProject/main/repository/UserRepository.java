package com.mySpringBootProject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mySpringBootProject.main.models.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{
	
	@Query("select u from UserInfo u where u.username=?1")

	UserInfo getByUsername(String username);
	
	

}
