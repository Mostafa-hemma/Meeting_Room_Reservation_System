package com.backend.fullProject.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

//id floor status off id emp id
@Entity
@Table(name="meetingRoom")
public class MeetingRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String floor;
	
	@Column
	private String status;
	
//	@ManyToOne
//	@JoinColumn(name = "officeId")
//	private Office office;
	
	@Column
	private int officeId;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "meetingRooms")
	private List<Employee> employees;
	
//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "meetingRoom")
//	private List<Events> events;
	
	public MeetingRoom() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public Office getOffice() {
//		return office;
//	}
//
//	public void setOffice(Office office) {
//		this.office = office;
//	}
	
	

	public List<Employee> getEmployees() {
		return employees;
	}

	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	

//	public List<Events> getEvents() {
//		return events;
//	}
//
//	public void setEvents(List<Events> events) {
//		this.events = events;
//	}

//	@Override
//	public String toString() {
//		return "MeetingRoom [id=" + id + ", floor=" + floor + ", status=" + status + ", office=" + office
//				+ ", employees=" + employees + ", events=" + events + "]";
//	}
	
	
	

}
