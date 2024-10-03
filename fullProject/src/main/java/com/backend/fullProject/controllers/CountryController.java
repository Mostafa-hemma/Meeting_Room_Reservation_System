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
import com.backend.fullProject.entity.Country;
import com.backend.fullProject.model.CountryResponse;
import com.backend.fullProject.service.CountryService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	

	
	@GetMapping(value="/country")
	public ResponseEntity<?> getAll(){
		List<CountryDto> countries=null;
		List<Country> myCountries=new ArrayList<Country>();
		try {
			myCountries=countryService.getAllCountries();
			countries=new ArrayList<CountryDto>();
			for(int i=0;i<myCountries.size();i++) {
				countries.add(new CountryDto(myCountries.get(i)));
			}
			return new ResponseEntity (new CountryResponse("all countries retrived successfully", countries),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new CountryResponse("Something went wrong", countries),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/country/{id}")
	public ResponseEntity<?> getById(@PathVariable int id){
		List<CountryDto> countries=null;
		Country myCountry=new Country();
		try {
			myCountry=countryService.getById(id);
			countries=new ArrayList<CountryDto>();
			
			countries.add(new CountryDto(myCountry));
			return new ResponseEntity (new CountryResponse("country with id : "+myCountry.getId()+" is retrieved successfully", countries),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new CountryResponse("Something went wrong", countries),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/country")
	public ResponseEntity<?> addCountry(@RequestBody CountryDto myNewCountry){
		List<CountryDto> countries=new ArrayList<CountryDto>();
		Country myCountry=new Country();
		Country savedCountry=new Country();
		myCountry.setId(0);
		myCountry.setName(myNewCountry.getName());

		try {
			savedCountry=countryService.addCountry(myCountry);
			countries.add(new CountryDto(savedCountry));
			return new ResponseEntity (new CountryResponse("country with id : "+savedCountry.getId()+"  is added successfully", countries),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new CountryResponse("Something went wrong"),HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping(value="country")
	public ResponseEntity<?> updateCountry(@RequestBody CountryDto updatedCountry)
	{
		List<CountryDto> countries=new ArrayList<CountryDto>();
		Country myCountry=new Country();
		Country savedCountry=new Country();
		
		try {
			myCountry=countryService.getById(updatedCountry.getId());
			myCountry.setName(updatedCountry.getName());
			savedCountry=countryService.editCountry(myCountry);
			countries.add(new CountryDto(savedCountry));
			return new ResponseEntity (new CountryResponse("country with id : "+savedCountry.getId()+"  is updated successfully", countries),HttpStatus.OK);
		
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity (new CountryResponse("Something went wrong"),HttpStatus.NOT_FOUND);
		
		}
	}

}
