package com.backend.fullProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.fullProject.entity.Employee;
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	
	public Employee findByEmail(String email);
	
	@Query("SELECT e FROM Employee e LEFT JOIN FETCH e.meetingRooms WHERE e.id = :id")
    Optional<Employee> findByIdWithMeetingRooms(Integer id);
	
	public List<Employee> findByRole(String role);
	
}
