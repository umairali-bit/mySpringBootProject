
// need to review

package com.mySpringBootProject.main.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mySpringBootProject.main.models.Customer;
import com.mySpringBootProject.main.models.CustomerProduct;
import com.mySpringBootProject.main.models.Product;
import com.mySpringBootProject.main.repository.CustomerProductRepository;
import com.mySpringBootProject.main.repository.CustomerRepository;
import com.mySpringBootProject.main.repository.ProductRepository;



@RestController

public class CustomerProductController {
	
	@Autowired
	private CustomerProductRepository customerProductRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/customer/product/{cid}/{pid}")
	public CustomerProduct purchaseAPi(@PathVariable("cid") Long cid, @PathVariable("pid") Long pid,
			@RequestBody CustomerProduct cp) {

		Optional<Customer> optionalC = customerRepository.findById(cid);
		if(!optionalC.isPresent()) 
			throw new RuntimeException("Invalid Customer ID Given");
		
		Optional<Product> optionalP = productRepository.findById(pid);
		if(!optionalP.isPresent()) 
			throw new RuntimeException("Invalid Product ID Given");
		
		Customer c = optionalC.get();
		Product p = optionalP.get();
		
		cp.setCustomer(c);
		cp.setProduct(p);
		
		cp.setDateOfPurchase(LocalDate.now());
		
		return customerProductRepository.save(cp);
		
	}
	
	@GetMapping("/product/customer/{cid}")
	public List<String> getProductsByCustomerId(@PathVariable("cid") Long cid) {
		Optional<Customer> optionalC = customerRepository.findById(cid);
		if(!optionalC.isPresent())
			throw new RuntimeException("Customer ID Invalid");
		
		Customer c = optionalC.get();
		
		List<String> list = customerProductRepository.getProductsByCustomerId(c.getId());
		return list; 
	}
	
	@GetMapping("/customer/product/{pid}")
	public List<Customer> getCustomersByProductId(@PathVariable("pid") Long pid) {
		Optional<Product> optionalP = productRepository.findById(pid);
		if(!optionalP.isPresent())
			throw new RuntimeException("Product ID invalid");
		
		List<Customer> list = (List)customerProductRepository.getCustomersByProductIdNative(pid);
		
		List<Customer> list1 = customerProductRepository.getCustomersByProductId(pid);
		return list1; 
	}
	
	/*
	 *  select customers that have purchased products sold by 'ABC Electronics'
	 */
	@GetMapping("/customer/vendor/{name}")
	public List<Customer> getCustomersByVendorName(@PathVariable("name") String name) {
		//return customerProductRepository.getCustomersByVendorName(name);
		return customerProductRepository.getCustomersByVendorNameAlternative(name);
	}
}




























