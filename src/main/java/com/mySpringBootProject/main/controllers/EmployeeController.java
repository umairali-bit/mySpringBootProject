package com.mySpringBootProject.main.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mySpringBootProject.main.DTO.EmployeeStatDto;
import com.mySpringBootProject.main.models.Employee;
import com.mySpringBootProject.main.repository.EmployeeRepository;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/employee")
	public Employee postEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployee(
			@RequestParam("page") Integer page,
			@RequestParam("size") Integer size){
		
		Pageable pageable=PageRequest.of(page, size);
		
		return employeeRepository.findAll(pageable).getContent();
	}
	
	@GetMapping("/employee/stats")
	public List<EmployeeStatDto> getEmployeeStats() {
		List<Employee> list = employeeRepository.findAll(); 
		System.out.println(list);
		List<EmployeeStatDto> listDto = new ArrayList<>();
		Map<String,Integer> statMap = new HashMap<>();
		Map<String, List<Employee>> map 
					= list.stream().collect(Collectors.groupingBy(e->e.getDepartment()));
		
		for(Map.Entry<String, List<Employee>> e: map.entrySet()) {
			EmployeeStatDto dto = new EmployeeStatDto();
			dto.setDepartment(e.getKey());
			dto.setCount(e.getValue().size());
			listDto.add(dto);
		}
		return listDto; 
	}
}
