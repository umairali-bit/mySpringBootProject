package com.mySpringBootProject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mySpringBootProject.main.models.CustomerProduct;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Long>{

}
