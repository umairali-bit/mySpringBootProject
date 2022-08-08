package com.mySpringBootProject.main.controllers;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Base64;

import com.mySpringBootProject.main.DTO.UserDto;
import com.mySpringBootProject.main.DTO.UserEditDto;
import com.mySpringBootProject.main.DTO.UserInfoDTO;
import com.mySpringBootProject.main.models.UserInfo;
import com.mySpringBootProject.main.repository.UserRepository;



@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@PostMapping("/user")
	
	/*
	public UserInfo postUser(@RequestBody UserInfo user) {
		// check if username exisits
		String password = user.getPassword();
		password = passwordEncoder.encode(password);
		user.setPassword(password);
		return userRepository.save(user);
	} */
	
	public void postUser(@RequestBody UserDto userDto) {
		String str = new String(Base64.getDecoder().decode(userDto.getEncodedCredentials())); 
		String username = str.split("@=")[0];
		String password = str.split("@=")[1];
		
		UserInfo info = new UserInfo();
		info.setName(userDto.getName());
		info.setPassword(passwordEncoder.encode(password));
		info.setUsername(username);
		info.setPasswordLastReset(LocalDate.now());
		info.setSecurityQuestion(userDto.getSecurityQuestion());
		info.setSecurityAnswer(userDto.getSecurityAnswer());
		info.setRole(userDto.getRole());

		 userRepository.save(info); 
		
		
	}
	
	@GetMapping("/login") //get username and password from spring
	public UserInfoDTO login(Principal principal) {
		String username = principal.getName();
		UserInfo info = userRepository.getByUsername(username);
		UserInfoDTO dto = new UserInfoDTO();
		dto.setId(info.getId());
		dto.setName(info.getName());
		dto.setUsername(info.getUsername());
		dto.setRole(info.getRole());
		
		return dto;
		
	}
	
	@GetMapping("/user/username")
	public UserEditDto getUserByUsername(Principal principal) {
		
		UserInfo info = userRepository.getByUsername(principal.getName());
		UserEditDto dto = new UserEditDto(info.getId(), info.getName(), 
						info.getSecurityQuestion(), info.getSecurityAnswer());
		
		return dto;
		
	}
	
	@PutMapping("/user/profile")
	public void profileEdit(Principal principal, @RequestBody UserDto dto) {
		String username = principal.getName();
		userRepository.updateProfile(username,dto.getName(),dto.getSecurityQuestion(),
									dto.getSecurityAnswer());
	}
	
	
	@GetMapping("/user/security/info/{username}")
	public UserEditDto getUserInfo(@PathVariable("username") String username) {
		UserInfo info =userRepository.getByUsername(username);
		UserEditDto dto = new UserEditDto(info.getId(), info.getName(), 
				"", info.getSecurityQuestion());
		return dto; 
	} 
	
	@GetMapping("/validate-security-answer/{encodedText}")
	public boolean verifySecurityAnswer(@PathVariable("encodedText") String encodedText) {
		boolean status=false;
		String str = new String(Base64.getDecoder().decode(encodedText)); 
		//username + '--'+answer
		String[] sarr =str.split("--");
		String username = sarr[0]; 
		String answer=sarr[1];
		UserInfo info =userRepository.getByUsername(username);
		if(info.getSecurityAnswer().equalsIgnoreCase(answer)) {
			status=true; 
		}
		return status; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
