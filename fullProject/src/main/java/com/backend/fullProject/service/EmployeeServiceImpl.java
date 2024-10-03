package com.backend.fullProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.fullProject.dao.EmployeeDao;
import com.backend.fullProject.entity.Employee;
import com.backend.fullProject.entity.MeetingRoom;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee getById(int id) throws Exception {
		return employeeDao.findById(id).orElseThrow(()-> new Exception());
	}

	@Override
	public List<Employee> getAll() throws Exception {
		return employeeDao.findAll();
	}

	@Override
	public Employee addEmployee(Employee myEmployee) throws Exception {
		return employeeDao.save(myEmployee);
	}

	@Override
	public Employee updateEmployee(Employee updatedEmployee) throws Exception {
		return employeeDao.save(updatedEmployee);
	}

	@Override
	public void deleteEmployee(int id) throws Exception {
		employeeDao.deleteById(id);
	}

	@Override
	public Employee findByEmail(String email) throws Exception {
		return employeeDao.findByEmail(email);
	}

	@Override
	public List<MeetingRoom> getMeetingRoomsByEmployeeId(int empId) throws Exception {
		// TODO Auto-generated method stub
		  return employeeDao.findByIdWithMeetingRooms(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"))
                .getMeetingRooms();
	}

	@Override
	public List<Employee> getAllEmployeesByRole(String role) throws Exception {
		// TODO Auto-generated method stub
		return employeeDao.findByRole(role);
	}

}
