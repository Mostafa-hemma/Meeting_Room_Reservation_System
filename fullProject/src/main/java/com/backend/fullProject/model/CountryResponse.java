package com.backend.fullProject.model;

import java.util.List;

import com.backend.fullProject.dto.CountryDto;

public class CountryResponse {
	
	private String message;
	private List<CountryDto> data;
	
	public CountryResponse(String message,List<CountryDto> data) {
		this.message=message;
		this.data=data;
	}
	public CountryResponse(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<CountryDto> getData() {
		return data;
	}
	public void setData(List<CountryDto> data) {
		this.data = data;
	}

}
