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

import com.backend.fullProject.dto.CountryDto;
import com.backend.fullProject.dto.GovernmentDto;
import com.backend.fullProject.entity.Country;
import com.backend.fullProject.entity.Government;
import com.backend.fullProject.model.CountryResponse;
import com.backend.fullProject.model.GovernmentRequest;
import com.backend.fullProject.service.GovernmentService;


@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class GovernmentController {
	
	@Autowired
	private GovernmentService govService;;
	
	@GetMapping(value="/government")
	public ResponseEntity<?> getAllGovernment(){
		List<Government> myGovernments=new ArrayList<Government>();
		List<GovernmentDto> myGovernmentDto=new ArrayList<GovernmentDto>();
		try {
			
			myGovernments=govService.getAllGov();
			for(int i=0;i<myGovernments.size();i++) {
				myGovernmentDto.add(new GovernmentDto(myGovernments.get(i)));
			}
			return new ResponseEntity (new GovernmentRequest("All Governments retrived Successfully",myGovernmentDto), HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new GovernmentRequest("There is no Employees in the DataBase"), HttpStatus.NOT_FOUND);

		}
	}
	
//	
	@GetMapping(value="government/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		Government myGovernment;
		List<GovernmentDto> myGovernments=new ArrayList<GovernmentDto>();
		
		try {
			myGovernment=govService.getById(id);
			myGovernments.add(new GovernmentDto(myGovernment));
			return new ResponseEntity (new GovernmentRequest(" Gov with id : "+ id +" retrived Successfully",myGovernments), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new GovernmentRequest("There is no Gov with this is id : "+id), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="government/country/{countryId}")
	public ResponseEntity<?> getByCountryId(@PathVariable int countryId){
		List<Government> myGovernments=new ArrayList<Government>();
		List<GovernmentDto> myGovernmentDto=new ArrayList<GovernmentDto>();
		try {
			
			myGovernments=govService.findByCountryId(countryId);
			for(int i=0;i<myGovernments.size();i++) {
				myGovernmentDto.add(new GovernmentDto(myGovernments.get(i)));
			}
			return new ResponseEntity (new GovernmentRequest("All Governments retrived Successfully",myGovernmentDto), HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new GovernmentRequest("There is no Employees in the DataBase"), HttpStatus.NOT_FOUND);

		}
	}
	
//	
	@PostMapping(value="/government")
	public ResponseEntity<?> addGov(@RequestBody GovernmentDto myNewGov){
		Government savedGovernment=new Government();
		Government newGovernment=new Government();
		List<GovernmentDto> myGovernments=new ArrayList<GovernmentDto>();
		newGovernment.setId(0);
		newGovernment.setName(myNewGov.getName());
		try {
			savedGovernment=govService.addGov(newGovernment);
			myGovernments.add(new GovernmentDto(savedGovernment));
			return new ResponseEntity (new GovernmentRequest(" Gov with id : "+ savedGovernment.getId() +" added Successfully",myGovernments), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new GovernmentRequest("something went wrong"), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PutMapping(value="/government")
	public ResponseEntity<?> updateGovernment(@RequestBody GovernmentDto updatedGovernment)
	{
		List<GovernmentDto> governments=new ArrayList<GovernmentDto>();
		Government myGovernment=new Government();
		Government savedGovernment=new Government();
		
		try {
			myGovernment=govService.getById(updatedGovernment.getId());
			myGovernment.setName(updatedGovernment.getName());
			savedGovernment=govService.updateGov(myGovernment);
			governments.add(new GovernmentDto(savedGovernment));
			return new ResponseEntity (new GovernmentRequest("Gov with id : "+savedGovernment.getId()+"  is updated successfully", governments),HttpStatus.OK);
		
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new GovernmentRequest("Something went wrong"),HttpStatus.NOT_FOUND);
		
		}
	}

}
