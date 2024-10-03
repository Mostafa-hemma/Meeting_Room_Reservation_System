package com.backend.fullProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.backend.fullProject.dao.MeetingRoomDao;
import com.backend.fullProject.entity.Events;

public class EventsDto {
	

	private int id;
	private String name;
	
	
	private LocalTime startTime;
	
	
	private LocalTime endTime;
	
	
	private LocalDate date;
	
	private String status;
	
	private String description;
	
	private int meetingRoomId;
	private EmployeeDto employeeDto;
	private MRDto roomDto;
	private int employeeId;
	
	public EventsDto() {}
	public EventsDto(Events event) {
		this.id=event.getId();
		this.status=event.getStatus();
		this.name=event.getName();
		this.description=event.getDescription();
		this.date=event.getDate();
		this.startTime=event.getStartTime();
		this.endTime=event.getEndTime();
		this.employeeId=event.getEmployeeId();
		this.meetingRoomId=event.getMeetingRoomId();
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
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMeetingRoomId() {
		return meetingRoomId;
	}
	public void setMeetingRoomId(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public MRDto getRoomDto() {
		return roomDto;
	}
	public void setRoomDto(MRDto roomDto) {
		this.roomDto = roomDto;
	}
	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}
	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

	
	

}
