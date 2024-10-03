package com.backend.fullProject.dto;

import java.util.List;

import com.backend.fullProject.entity.Employee;
import com.backend.fullProject.entity.Events;
import com.backend.fullProject.entity.MeetingRoom;

public class EmployeeDto {
	

	private int id;
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String email;
	
	
	private String password;
	
	
	private String site;
	
	
	private String role;
	
	private int officeId;

//	private List<MeetingRoom> meetingRooms;
//	
//
//	private List<Events> events;
	
	public EmployeeDto() {}
	
	public EmployeeDto(Employee employee) {
		
		this.id=employee.getId();
		this.firstName=employee.getFirstName();
		this.lastName=employee.getLastName();
		this.email=employee.getEmail();
		this.password=employee.getPassword();
		this.site=employee.getSite();
		this.role=employee.getRole();
		this.officeId=employee.getOfficeId();
//		this.meetingRooms=employee.getMeetingRooms();
//		this.events=employee.getEvents();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}

//	public List<MeetingRoom> getMeetingRooms() {
//		return meetingRooms;
//	}
//
//	public void setMeetingRooms(List<MeetingRoom> meetingRooms) {
//		this.meetingRooms = meetingRooms;
//	}
//
//	public List<Events> getEvents() {
//		return events;
//	}
//
//	public void setEvents(List<Events> events) {
//		this.events = events;
//	}
//	
	

}
