package com.mySpringBootProject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mySpringBootProject.main.models.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{

}