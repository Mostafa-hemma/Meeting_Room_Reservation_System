package com.backend.fullProject.model;

import java.util.List;

import com.backend.fullProject.dto.MRDto;

public class MRResponse {
	
	
		
		
			
			private String message;
			private List<MRDto> data;
			
			public MRResponse(String message,List<MRDto> data) {
				this.message=message;
				this.data=data;
			}
			public MRResponse(String message) {
				this.message=message;
			}
			public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
			public List<MRDto> getData() {
				return data;
			}
			public void setData(List<MRDto> data) {
				this.data = data;
			}

		


	


}
