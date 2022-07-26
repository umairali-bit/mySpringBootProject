package com.mySpringBootProject.main.DTO;

import java.util.List;

public class VendorDto {
	
	private Long id;
	private String name;
	private Integer numProducts;
	
	
	
	List<ProductDto> products;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getNumProducts() {
		return numProducts;
	}



	public void setNumProducts(Integer numProducts) {
		this.numProducts = numProducts;
	}



	public List<ProductDto> getProducts() {
		return products;
	}



	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	
	
	

}
