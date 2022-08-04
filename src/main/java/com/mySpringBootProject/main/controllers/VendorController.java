package com.mySpringBootProject.main.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mySpringBootProject.main.DTO.ProductDto;
import com.mySpringBootProject.main.DTO.VendorDto;
import com.mySpringBootProject.main.models.Product;
import com.mySpringBootProject.main.models.Vendor;
import com.mySpringBootProject.main.repository.ProductRepository;
import com.mySpringBootProject.main.repository.VendorRepository;

@RestController
public class VendorController {

	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/vendor")
	public void postVendor(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
	}
	
	@GetMapping("/vendor") //page = 0, (1-20), 1(21-40), 2(41-60), 3(61-80) [/vendor?page=0&size=3]
	
	
	/* to get all the vendor in JSON format
	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}
	*/
	
	/* to limit result of vendor till 20 use pagination */
	
	public List<VendorDto> getAllVendors(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
									  @RequestParam(name = "size", required = false, defaultValue = "10000") Integer size) {
		
		PageRequest pageable = PageRequest.of(page,size);
		List <Vendor> list = vendorRepository.findAll(pageable).getContent();
		List<VendorDto> listVDto = new ArrayList<>();
		
		List<Product> listProducts = productRepository.findAll();
		list.stream().forEach(v-> {
			List<ProductDto> listPDto = new ArrayList<>();
			VendorDto vDto = new VendorDto();
			vDto.setId(v.getId());
			vDto.setName(v.getName());
			List<Product> filteredList = listProducts.stream()
										.filter(p-> p.getVendor().getId().equals(v.getId()))
										.collect(Collectors.toList());
			vDto.setNumProducts(filteredList.size());
			filteredList.stream().forEach(p->{
				ProductDto dto = new ProductDto();
				dto.setId(p.getId());
				dto.setName(p.getName());
				dto.setPrice(p.getPrice());
				dto.setCid(p.getCategory().getId());
				dto.setCname(p.getCategory().getName());
				dto.setCpref(p.getCategory().getPreference());
				dto.setVid(p.getCategory().getId());
				dto.setVcity(p.getVendor().getCity());
				dto.setVname(p.getCategory().getName());
				listPDto.add(dto);
				
			
		});
		vDto.setProducts(listPDto);
		listVDto.add(vDto);
		});
		return listVDto;
	}
	
	@GetMapping("/vendor/{id}") //<-- path variable : /vendor/7
	public Vendor getVendorById(@PathVariable("id") Long id) { //id=7
		Optional<Vendor> optional = vendorRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		else
			throw new RuntimeException("ID is invalid");
	}
	
	
	
	@PutMapping("/vendor/{id}")
	public Vendor updateVendor (@PathVariable("id")Long id,
			@RequestBody Vendor newVendor) {
		
		Optional<Vendor> optional = vendorRepository.findById(id);
		if(optional.isPresent()) {
			Vendor existingVendor = optional.get();
			existingVendor.setCity(newVendor.getCity());
			existingVendor.setName(newVendor.getName());
			return vendorRepository.save(existingVendor);
			
		}
		else
			throw new RuntimeException("ID is inavlid");
		
	}
	
	@GetMapping("/vendor/city/{city}")
	public List<Vendor> getVendorByCity(@PathVariable("city") String city) {
		List<Vendor>list = vendorRepository.findByCity(city);
		return list;
	}
	
	
	@DeleteMapping("/vendor/{vid}")
	public void deleteVendor(@PathVariable("vid") Long vid) {
		
		productRepository.deleteProductByVendorId(vid);
		vendorRepository.deleteById(vid);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}