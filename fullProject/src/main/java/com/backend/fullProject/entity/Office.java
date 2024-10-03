package com.backend.fullProject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//id name address gov id

@Entity
@Table(name="office")
public class Office {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="governmentId")
//	private Government government;
	@Column
	private int governmentId;
	
//	@OneToMany(mappedBy = "office")
//	private List<MeetingRoom> meetingRooms;
	
	public Office() {}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public Government getGovernment() {
//		return government;
//	}
//
//	public void setGovernment(Government government) {
//		this.government = government;
//	}
	
//
//	public List<MeetingRoom> getMeetingRooms() {
//		return meetingRooms;
//	}

	public int getGovernmentId() {
		return governmentId;
	}

	public void setGovernmentId(int governmentId) {
		this.governmentId = governmentId;
	}

//	public void setMeetingRooms(List<MeetingRoom> meetingRooms) {
//		this.meetingRooms = meetingRooms;
//	}
	
	
	

}
