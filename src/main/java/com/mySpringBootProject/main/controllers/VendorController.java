package com.mySpringBootProject.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mySpringBootProject.main.models.Vendor;
import com.mySpringBootProject.main.repository.CategoryRepository;
import com.mySpringBootProject.main.repository.VendorRepository;

@RestController
public class VendorController {

	@Autowired
	private VendorRepository vendorRepository;
	
	@PostMapping("/vendor")
	public void postVendor(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
	}
	
	@GetMapping("/vendor")
	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}
	
	@GetMapping("/vendor/{id}") //<-- path variable : /vendor/7
	public Vendor getVendorById(@PathVariable("id") Long id) { //id=7
		Optional<Vendor> optional = vendorRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		else
			throw new RuntimeException("ID is invalid");
	}
	
	//Delete by ID
	
	@DeleteMapping("/vendor/{id}")
	public void deleteVendor(@PathVariable("id")Long id){
		vendorRepository.deleteById(id);
		
	}
	
	@PutMapping("/vendor/{id}")
	public void updateVendor (@PathVariable("id")Long id,
			@RequestBody Vendor newVendor) {
		
		Optional<Vendor> optional = vendorRepository.findById(id);
		if(optional.isPresent()) {
			Vendor existingVendor = optional.get();
			existingVendor.setCity(newVendor.getCity());
			existingVendor.setName(newVendor.getName());
			
		}
		else
			throw new RuntimeException("ID is inavlid");
		
	}
	
	@GetMapping("/vendor/city/{city}")
	public List<Vendor> getVendorByCity(@PathVariable("city") String city) {
		List<Vendor>list = vendorRepository.findByCity(city);
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}