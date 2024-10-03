package com.backend.fullProject.service;

import java.util.List;

import com.backend.fullProject.entity.Employee;
import com.backend.fullProject.entity.MeetingRoom;

public interface EmployeeService {
	
	public Employee getById(int id) throws Exception;
	public List<Employee> getAll() throws Exception;
	public Employee addEmployee(Employee myEmployee) throws Exception;
	public Employee updateEmployee(Employee updatedEmployee)throws Exception;
	public void deleteEmployee(int id) throws Exception;
	public Employee findByEmail(String email)throws Exception;
	public List<MeetingRoom> getMeetingRoomsByEmployeeId(int empId)throws Exception;
	public List<Employee> getAllEmployeesByRole(String role)throws Exception;
}
