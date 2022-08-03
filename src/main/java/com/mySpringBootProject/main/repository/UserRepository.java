package com.mySpringBootProject.main.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mySpringBootProject.main.models.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{
	
	@Query("select u from UserInfo u where u.username=?1")
	UserInfo getByUsername(String username);
	
	
	@Transactional
	@Modifying
	@Query("update UserInfo u SET u.name=?2,u.securityQuestion=?3,u.securityAnswer=?4 "
			 + " where u.username=?1")
	void updateProfile(String username, String name, String securityQuestion, String securityAnswer);
	
	

}
