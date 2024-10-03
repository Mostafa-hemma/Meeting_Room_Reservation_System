package com.backend.fullProject.model;

import java.util.List;

import com.backend.fullProject.dto.EventsDto;

public class EventsResponse {
	
	
		private String message;
		private List<EventsDto> data;
		
		public EventsResponse(String message,List<EventsDto> data) {
			this.message=message;
			this.data=data;
		}
		public EventsResponse(String message) {
			this.message=message;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<EventsDto> getData() {
			return data;
		}
		public void setData(List<EventsDto> data) {
			this.data = data;
		}
		
	}


