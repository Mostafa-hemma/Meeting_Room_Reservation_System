package com.backend.fullProject.dto;

import com.backend.fullProject.entity.Office;

public class OfficeDto {
	
	private int id;
	private String name;
	private String address;
	private int govId;
	
	public OfficeDto() {}

	public OfficeDto( Office myOffice) {
		this.id=myOffice.getId();
		this.name = myOffice.getName();
		this.address = myOffice.getAddress();
		this.govId = myOffice.getGovernmentId();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getGovId() {
		return govId;
	}

	public void setGovId(int govId) {
		this.govId = govId;
	}

	
	
	

}
