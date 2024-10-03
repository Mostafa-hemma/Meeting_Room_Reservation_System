package com.backend.fullProject.model;

import java.util.List;

import com.backend.fullProject.dto.OfficeDto;

public class OfficeResponse {
	
	
		
		private String message;
		private List<OfficeDto> data;
		
		public OfficeResponse(String message,List<OfficeDto> data) {
			this.message=message;
			this.data=data;
		}
		public OfficeResponse(String message) {
			this.message=message;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<OfficeDto> getData() {
			return data;
		}
		public void setData(List<OfficeDto> data) {
			this.data = data;
		}

	


}
