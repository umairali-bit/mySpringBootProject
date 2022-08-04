package com.mySpringBootProject.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mySpringBootProject.main.models.Category;
import com.mySpringBootProject.main.repository.CategoryRepository;


@RestController
public class CategoryController {

	@Autowired //<- Spring will wire it to CategoryRepository interface. 
	private CategoryRepository categoryRepository; 
	
	
	@PostMapping("/category")
	public void postCategory(@RequestBody Category category) {
		//we use JpaRepository Interface 
		categoryRepository.save(category);
	}
	
	// Example
	
	@GetMapping("/hello")    //REST API: Representational State Transfer 
	public String sayHello() {
		return "Hello All!!! welcome to spring!";
	}
	
	
	
	@GetMapping("/category")
	public List<Category> getAllCategories() {
		List<Category> list = categoryRepository.findAll();
		return list; 
	}
	
	@GetMapping("/category/{id}") //category/single/4
	public Category getSingleCategoryById(@PathVariable("id") Long id) {
		Optional<Category> optional =  categoryRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
		  
	}
	@DeleteMapping("/category/{id}")
	public void deleteCategory(@PathVariable("id") Long id) {
		categoryRepository.deleteById(id);
	}
	@PutMapping("/category/{id}")
	public Category updateCategory(@PathVariable("id") Long id,
			@RequestBody Category newCategory) {
		Optional<Category> optional =categoryRepository.findById(id);
		if(optional.isPresent()) {
			Category existingCategory =  optional.get();
			existingCategory.setName(newCategory.getName());
			existingCategory.setPreference(newCategory.getPreference());
			return categoryRepository.save(existingCategory);
		}
		else	
			throw new RuntimeException("ID is invalid");
	}
	
	@GetMapping("/category/pref/{pref}")
	public List<Category> getCategoriesByPref(@PathVariable("pref") Integer preference) {
		List<Category> list = categoryRepository.findByPreference(preference);
		return list;
	}
}


