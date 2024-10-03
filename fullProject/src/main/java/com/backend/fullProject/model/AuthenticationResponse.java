package com.backend.fullProject.model;

import com.backend.fullProject.dto.EmployeeDto;
import com.backend.fullProject.entity.Employee;

public class AuthenticationResponse {

	private String message;
	private String jwt;
	private EmployeeDto myEmployee;
	private String officeName;
	private String countryName;
	private String govName;

//	public AuthenticationResponse(String message, String jwt, EmployeeDto myEmployee,) {
//		this.jwt = jwt;
//		this.message = message;
//		this.myEmployee=myEmployee;
//	}

	
	public AuthenticationResponse(String message) {
		this.message = message;
	}

	public AuthenticationResponse(String message, String jwt, EmployeeDto myEmployee, String officeName, String countryName,
		String govName) {
	this.message = message;
	this.jwt = jwt;
	this.myEmployee = myEmployee;
	this.officeName = officeName;
	this.countryName = countryName;
	this.govName = govName;
}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmployeeDto getMyEmployee() {
		return myEmployee;
	}

	public void setMyEmployee(EmployeeDto myEmployee) {
		this.myEmployee = myEmployee;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getGovName() {
		return govName;
	}

	public void setGovName(String govName) {
		this.govName = govName;
	}



}
