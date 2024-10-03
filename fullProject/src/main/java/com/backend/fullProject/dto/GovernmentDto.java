package com.backend.fullProject.dto;

import com.backend.fullProject.entity.Government;

public class GovernmentDto {
	
	private int id; 
	private String name;
	private int countryId;
	
	public GovernmentDto() {}
	
	public GovernmentDto(Government myGovernment) {
		this.id=myGovernment.getId();
		this.name=myGovernment.getName();
		this.countryId=myGovernment.getCountryId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	
	
	
}
