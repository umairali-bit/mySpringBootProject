package com.mySpringBootProject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mySpringBootProject.main.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}