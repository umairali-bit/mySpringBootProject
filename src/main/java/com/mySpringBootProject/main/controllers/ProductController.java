package com.mySpringBootProject.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mySpringBootProject.main.models.Category;
import com.mySpringBootProject.main.models.Product;
import com.mySpringBootProject.main.models.Vendor;
import com.mySpringBootProject.main.repository.CategoryRepository;
import com.mySpringBootProject.main.repository.ProductRepository;
import com.mySpringBootProject.main.repository.VendorRepository;;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private VendorRepository vendorRepository;

	@PostMapping("/product/{cid}/{vid}")
	public Product postProduct(@RequestBody Product product, @PathVariable("cid") Long cid,
			@PathVariable("vid") Long vid) {

		/* go to repo and fetch category by cid */
		Optional<Category> optional = categoryRepository.findById(cid);
		if (!optional.isPresent())
			throw new RuntimeException("Category ID is Invalid!!");

		Category category = optional.get();

		/* go to repo and fetch vendor by vid */
		Optional<Vendor> optionalV = vendorRepository.findById(vid);
		
		if(!optionalV.isPresent())
			throw new RuntimeException("Vendor ID is Invalid!!");
		
		Vendor vendor = optionalV.get();
		
		/* Attach category and vendor to the product */
		product.setCategory(category);
		product.setVendor(vendor);
		
		/* Save the product in the DB */
		return productRepository.save(product);
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		List<Product> list =  productRepository.findAll();
		return list;
	}
	
	@GetMapping("/products/category/{cid}")
	public List<Product> getProductsByCategoryId(@PathVariable("cid") Long cid){
		List<Product> list = productRepository.getProductsByCategoryId(cid);
		return list;
	}
	
	
	@GetMapping("/products/vendor/{vid}")
	public List<Product> getProductsByVendorId(){
		return null;
	}
}
