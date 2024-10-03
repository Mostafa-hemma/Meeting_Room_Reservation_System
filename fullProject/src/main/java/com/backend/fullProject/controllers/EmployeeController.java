package com.backend.fullProject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fullProject.dto.EmployeeDto;
import com.backend.fullProject.entity.Employee;
import com.backend.fullProject.model.EmployeeResponse;
import com.backend.fullProject.service.EmployeeService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value="/employees")
	public ResponseEntity<?> getAllEmployees(){
		List<Employee> myEmployees=new ArrayList<Employee>();
		List<EmployeeDto> myEmployeesDto=new ArrayList<EmployeeDto>();
		try {
			
			myEmployees=employeeService.getAll();
			for(int i=0;i<myEmployees.size();i++) {
				myEmployeesDto.add(new EmployeeDto(myEmployees.get(i)));
			}
			return new ResponseEntity (new EmployeeResponse("All Employees retrived Successfully",myEmployeesDto), HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new EmployeeResponse("There is no Employees in the DataBase"), HttpStatus.NOT_FOUND);

		}
	}
	
	@GetMapping(value="/employees/hrrole")
	public ResponseEntity<?> getAllEmployeesByRole(){

		List<Employee> myEmployees=new ArrayList<Employee>();
		List<EmployeeDto> myEmployeesDto=new ArrayList<EmployeeDto>();
		try {
			
			myEmployees=employeeService.getAllEmployeesByRole("ROLE_HR");
			for(int i=0;i<myEmployees.size();i++) {
				myEmployeesDto.add(new EmployeeDto(myEmployees.get(i)));
			}
			return new ResponseEntity (new EmployeeResponse("All Employees retrived Successfully",myEmployeesDto), HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new EmployeeResponse("There is no Employees in the DataBase"), HttpStatus.NOT_FOUND);

		}
	}
	
	
	@GetMapping(value="employees/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		Employee myEmployee;
		List<EmployeeDto> myEmployees=new ArrayList<EmployeeDto>();
		
		try {
			myEmployee=employeeService.getById(id);
			myEmployees.add(new EmployeeDto(myEmployee));
			return new ResponseEntity (new EmployeeResponse(" Employee with id : "+ id +" retrived Successfully",myEmployees), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new EmployeeResponse("There is no Employee with this is id : "+id), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="employees")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto myEmployeeDto){
		Employee myEmployee=new Employee();
		Employee employee=new Employee();
		myEmployee.setId(0);
		myEmployee.setFirstName(myEmployeeDto.getFirstName());
		myEmployee.setLastName(myEmployeeDto.getLastName());
		myEmployee.setEmail(myEmployeeDto.getEmail());
		myEmployee.setPassword(myEmployeeDto.getPassword());
		myEmployee.setRole(myEmployeeDto.getRole());
		myEmployee.setSite(myEmployeeDto.getSite());
		myEmployee.setOfficeId(myEmployeeDto.getOfficeId());
		List<EmployeeDto> myEmployees=new ArrayList<EmployeeDto>();
		try {
			employee=employeeService.addEmployee(myEmployee);
			myEmployees.add(new EmployeeDto(employee));
			return new ResponseEntity (new EmployeeResponse(" Employee  added Successfully",myEmployees), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new EmployeeResponse("employee couldnt added "), HttpStatus.NOT_FOUND);
		}

	}
	@PutMapping(value="/employees")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto myEmployeeDto){
		Employee myEmployee=new Employee();
		Employee myEmployeeee=new Employee();

		List<EmployeeDto> myEmployees=new ArrayList<EmployeeDto>();
		try {
			myEmployee=employeeService.getById(myEmployeeDto.getId());
			myEmployee.setFirstName(myEmployeeDto.getFirstName());
			myEmployee.setLastName(myEmployeeDto.getLastName());
			myEmployee.setEmail(myEmployeeDto.getEmail());
			myEmployee.setPassword(myEmployeeDto.getPassword());
			myEmployee.setRole(myEmployeeDto.getRole());
			myEmployee.setSite(myEmployeeDto.getSite());
			myEmployee.setOfficeId(myEmployeeDto.getOfficeId());

			myEmployeeee=employeeService.updateEmployee(myEmployee);
			EmployeeDto employee=new EmployeeDto(myEmployeeee);
			myEmployees.add(employee);
			return new ResponseEntity (new EmployeeResponse(" Employee  updated Successfully",myEmployees), HttpStatus.OK);

		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new EmployeeResponse("employee couldnt update "), HttpStatus.NOT_FOUND);
		}
		
	}

}
