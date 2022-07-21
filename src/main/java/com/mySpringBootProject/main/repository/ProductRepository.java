package com.mySpringBootProject.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mySpringBootProject.main.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select p from Product p where p.category.id=?1")
	List<Product> getProductsByCategoryId(Long cid);

}
