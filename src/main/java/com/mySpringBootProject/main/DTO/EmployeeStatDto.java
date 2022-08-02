package com.mySpringBootProject.main.DTO;

public class EmployeeStatDto {
	private String department;
	private Integer count;
	public String getDepartment() {
		return department;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	

}
