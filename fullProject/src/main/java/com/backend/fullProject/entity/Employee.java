package com.backend.fullProject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//id fn ln email password site role

@Entity
@Table(name = "employee")
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String site;
	
	@Column
	private String role;
	
	@Column
	private int officeId;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "empRoom",joinColumns = @JoinColumn(name = "empId"),
	inverseJoinColumns = @JoinColumn(name = "roomId"))
	private List<MeetingRoom> meetingRooms;
	
//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "employee")
//	private List<Events> events;
	
	
	public Employee() {}

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
	
	

	public List<MeetingRoom> getMeetingRooms() {
		return meetingRooms;
	}

	public void setMeetingRooms(List<MeetingRoom> meetingRooms) {
		this.meetingRooms = meetingRooms;
	}
	

//	public List<Events> getEvents() {
//		return events;
//	}
//
//	public void setEvents(List<Events> events) {
//		this.events = events;
//	}
	
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", site=" + site + ", role=" + role + "]";
	}

	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	
	
	

}
