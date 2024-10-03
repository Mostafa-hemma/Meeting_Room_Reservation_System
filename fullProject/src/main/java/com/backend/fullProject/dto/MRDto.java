package com.backend.fullProject.dto;

import java.util.ArrayList;
import java.util.List;

import com.backend.fullProject.entity.MeetingRoom;

public class MRDto {
	
	private int id;
	private String floor;
	private String status;
	private List<Integer> employeeId=new ArrayList<Integer>();
	private int officeId;
	private String officeName;
	public MRDto() {}
	
	public MRDto(MeetingRoom meetingRoom) {
		
		this.id=meetingRoom.getId();
		this.floor=meetingRoom.getFloor();
		this.status=meetingRoom.getStatus();
		this.officeId=meetingRoom.getOfficeId();
		for(int i=0;i<meetingRoom.getEmployees().size();i++) {
			employeeId.add(meetingRoom.getEmployees().get(i).getId());
		}
	}

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

	

	public List<Integer> getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(List<Integer> employeeId) {
		this.employeeId = employeeId;
	}

	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	
	
	
}
