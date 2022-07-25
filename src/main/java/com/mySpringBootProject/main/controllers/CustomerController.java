package com.mySpringBootProject.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mySpringBootProject.main.models.Customer;
import com.mySpringBootProject.main.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/customer")
	public Customer postCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
