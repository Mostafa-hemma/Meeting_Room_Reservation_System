package com.backend.fullProject.model;

import java.util.List;

import com.backend.fullProject.dto.EmployeeDto;
import com.backend.fullProject.entity.Employee;

public class EmployeeResponse {
	
	private String message;
	private List<EmployeeDto> data;
	
	public EmployeeResponse(String message,List<EmployeeDto> data) {
		this.message=message;
		this.data=data;
	}
	public EmployeeResponse(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<EmployeeDto> getData() {
		return data;
	}
	public void setData(List<EmployeeDto> data) {
		this.data = data;
	}
	
	

}
