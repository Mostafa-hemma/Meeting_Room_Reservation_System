package com.backend.fullProject.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// id name start time end time date description meeting room id emp id

@Entity
@Table(name="events")
public class Events {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private LocalTime startTime;
	
	@Column
	private LocalTime endTime;
	
	@Column
	private LocalDate date;
	
	@Column
	private String description;
	
	@Column
	private String status;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "meetingRoomId")
//	private MeetingRoom meetingRoom;
	@Column
	private int meetingRoomId;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="employeeId")
//	private Employee employee;
	@Column
	private int employeeId;
	
	public Events() {}

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

//	public MeetingRoom getMeetingRoom() {
//		return meetingRoom;
//	}
//
//	public void setMeetingRoom(MeetingRoom meetingRoom) {
//		this.meetingRoom = meetingRoom;
//	}
	
	

//	public Employee getEmployee() {
//		return employee;
//	}

	public int getMeetingRoomId() {
		return meetingRoomId;
	}

	public void setMeetingRoomId(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	
	
	
	
	

}
