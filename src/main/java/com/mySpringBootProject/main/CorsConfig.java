package com.mySpringBootProject.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
			String[] origins = new String[]{"http://localhost:4200"};
				registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins(origins);
		
	}

}


//localhost:4200 : methods: GET POST PUT DELETE
//headers: * (all the headers must be allowed)
//path api: everything next to / should be allowed