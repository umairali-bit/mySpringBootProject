package com.mySpringBootProject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mySpringBootProject.main.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
