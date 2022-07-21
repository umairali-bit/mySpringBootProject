package com.mySpringBootProject.main.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@Column(nullable = false)
	private String name; 
	
	@Column(nullable = false)
	private Double price; 
	
	@OneToOne
	private Category category;
	
	@OneToOne
	private Vendor vendor;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", vendor="
				+ vendor + "]";
	}
	
	
}
