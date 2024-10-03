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

import com.backend.fullProject.dto.GovernmentDto;
import com.backend.fullProject.dto.OfficeDto;
import com.backend.fullProject.entity.Government;
import com.backend.fullProject.entity.Office;
import com.backend.fullProject.model.GovernmentRequest;
import com.backend.fullProject.model.OfficeResponse;
import com.backend.fullProject.service.GovernmentService;
import com.backend.fullProject.service.OfficeService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class OfficeController {

	@Autowired
	private OfficeService officeService;
	@Autowired
	private GovernmentService govService;

	@GetMapping(value = "/office")
	public ResponseEntity<?> getAll() {
		List<Office> offices = new ArrayList<Office>();
		List<OfficeDto> myOffices = new ArrayList<OfficeDto>();
		try {
			offices = officeService.getAll();
			for (int i = 0; i < offices.size(); i++) {
				myOffices.add(new OfficeDto(offices.get(i)));
			}
			return new ResponseEntity(new OfficeResponse("all offices retrieved successfully ", myOffices),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new OfficeResponse("something went worng ", myOffices), HttpStatus.NOT_FOUND);

		}
	}
	
	@GetMapping(value="/office/{id}")
	public ResponseEntity getById(@PathVariable int id) {
		List<OfficeDto> officesDto=new ArrayList<OfficeDto>();
		Office myOffice=new Office();
		try {
			myOffice=officeService.getById(id);
			officesDto.add(new OfficeDto(myOffice));
			return new ResponseEntity(new OfficeResponse("Office with id :"+myOffice.getId()+" retrieved successfully ", officesDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new OfficeResponse("something went worng ", officesDto), HttpStatus.NOT_FOUND);

		}
	}
	@GetMapping(value="/office/government/{govId}")
	public ResponseEntity getByGovId(@PathVariable int govId) {
		List<Office> offices = new ArrayList<Office>();
		List<OfficeDto> myOffices = new ArrayList<OfficeDto>();
		try {
			offices = officeService.getByGovId(govId);
			for (int i = 0; i < offices.size(); i++) {
				myOffices.add(new OfficeDto(offices.get(i)));
			}
			return new ResponseEntity(new OfficeResponse("all offices retrieved successfully ", myOffices),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new OfficeResponse("something went worng ", myOffices), HttpStatus.NOT_FOUND);

		}
	}
	
	@PostMapping(value="/office")
	public ResponseEntity<?> addOffice(@RequestBody OfficeDto addedOffice){
		List<OfficeDto> officesDto=new ArrayList<OfficeDto>();
		Office newOffice=new Office();
		newOffice.setId(0);
		newOffice.setName(addedOffice.getName());
		newOffice.setAddress(addedOffice.getAddress());
		Office savedOffice=new Office();
		try {
			savedOffice=officeService.addOffice(newOffice);
			officesDto.add(new OfficeDto(savedOffice));
			return new ResponseEntity(new OfficeResponse("Office is added successfully ", officesDto),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new OfficeResponse("something went worng ", officesDto), HttpStatus.NOT_FOUND);

		}
	}
	
	@PutMapping(value="/office")
	public ResponseEntity<?> updateGovernment(@RequestBody OfficeDto updatedOffice)
	{
		List<OfficeDto> offices=new ArrayList<OfficeDto>();
		Office myOffice=new Office();
		Office savedOffice=new Office();
		
		try {
			myOffice=officeService.getById(updatedOffice.getId());
			myOffice.setName(updatedOffice.getName());
			myOffice.setAddress(updatedOffice.getAddress());
//			myOffice.setGovernment(govService.findByGovName(updatedOffice.getGovName()));
			myOffice.setGovernmentId(updatedOffice.getGovId());
			savedOffice=officeService.editOffice(myOffice);
			offices.add(new OfficeDto(savedOffice));
			return new ResponseEntity (new OfficeResponse("Office with id : "+savedOffice.getId()+"  is updated successfully", offices),HttpStatus.OK);
		
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new OfficeResponse("Something went wrong"),HttpStatus.NOT_FOUND);
		
		}
	}
	
	
}
