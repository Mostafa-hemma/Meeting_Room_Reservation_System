package com.backend.fullProject.model;

import java.util.List;

import com.backend.fullProject.dto.GovernmentDto;

public class GovernmentRequest {
	private String message;
	private List<GovernmentDto> data;
	
	public GovernmentRequest(String message,List<GovernmentDto> data) {
		this.message=message;
		this.data=data;
	}
	public GovernmentRequest(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<GovernmentDto> getData() {
		return data;
	}
	public void setData(List<GovernmentDto> data) {
		this.data = data;
	}
	
}
