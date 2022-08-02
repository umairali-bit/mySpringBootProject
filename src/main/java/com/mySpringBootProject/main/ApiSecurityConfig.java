package com.mySpringBootProject.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mySpringBootProject.main.Service.MyUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter{
	
		@Autowired
		private MyUserDetailsService myUserDetailsService;
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
	
				// configure our apis as per roles
			
			http.authorizeRequests()
				.antMatchers(HttpMethod.GET,"/login").authenticated() //we can use permitAll() if we dont want authentication
				//.antMatchers("/products").authenticated()
				//.antMatchers("/products/category/{cid}").hasAnyAuthority("ADMIN") //has any role
				.anyRequest().permitAll()
				.and().httpBasic()
				.and().csrf().disable();
			
			
			
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				
				// built our custom authManager
			
				
			
			/*
			 * 
			 * // imAMemory authenticationManager
			
			auth.inMemoryAuthentication()
				.withUser("hairy")
				.password(getPasswordEncoder().encode("potter123"))
				.roles("ADMIN")
				.and()
				.withUser("ronald")
				.password(getPasswordEncoder().encode("weasley123"))
				.roles("EXEC");
			
			*/
			
			auth.authenticationProvider(getCustomeProvider());
			
		}
	
		
		
	

		@Bean //auto generated encoder
		PasswordEncoder getPasswordEncoder() {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return passwordEncoder;
		
		}
		
		private DaoAuthenticationProvider getCustomeProvider() {
				DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
				dao.setPasswordEncoder(getPasswordEncoder());
				dao.setUserDetailsService(myUserDetailsService);
				return dao;
			}
		
		

		
	
	
	
	
	
	
	
	
	
	

}
