package com.mySpringBootProject.main.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mySpringBootProject.main.Enum.CouponCode;

@Entity
@Table(name ="customer_product")
public class CustomerProduct {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@OneToOne
	private Customer customer; //Generating customer_id (FK)
	
	@OneToOne
	private Product product; //Generating product_id (FK)
	
	
	
	private LocalDate dateOfPurchase;
	
	private Boolean couponUsed;
	
	
	@Enumerated(EnumType.STRING)
	private CouponCode couponCode;

	public CustomerProduct() {
		super();
		
	}

	public CustomerProduct(Long id, Customer customer, Product product, LocalDate dateOfPurchase, Boolean couponUsed,
			CouponCode couponCode) {
		super();
		this.id = id;
		this.customer = customer;
		this.product = product;
		this.dateOfPurchase = dateOfPurchase;
		this.couponUsed = couponUsed;
		this.couponCode = couponCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Boolean getCouponUsed() {
		return couponUsed;
	}

	public void setCouponUsed(Boolean couponUsed) {
		this.couponUsed = couponUsed;
	}

	public CouponCode getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(CouponCode couponCode) {
		this.couponCode = couponCode;
	}

	@Override
	public String toString() {
		return "CustomerProduct [id=" + id + ", customer=" + customer + ", product=" + product + ", dateOfPurchase="
				+ dateOfPurchase + ", couponUsed=" + couponUsed + ", couponCode=" + couponCode + "]";
	}
	
	

	
	
	
	
	
	
	
	
	

}
