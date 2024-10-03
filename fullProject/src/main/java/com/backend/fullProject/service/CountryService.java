package com.backend.fullProject.service;

import java.util.List;

import com.backend.fullProject.entity.Country;

public interface CountryService {
	
	public List<Country> getAllCountries() throws Exception;
	public Country getById(int id) throws Exception;
	public Country addCountry(Country country) throws Exception;
	public Country editCountry(Country country) throws Exception;
	public void deleteCountry(int id) throws Exception;
	public Country findCountryByName(String countryName)throws Exception;

}
