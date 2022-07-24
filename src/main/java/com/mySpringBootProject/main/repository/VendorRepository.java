package com.mySpringBootProject.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mySpringBootProject.main.models.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
	
	@Query("select v from Vendor v where v.city=?1")
	List<Vendor> findByCity(String city);

}