package com.backend.fullProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.fullProject.dao.CountryDao;
import com.backend.fullProject.entity.Country;
@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryDao countryDao;

	@Override
	public List<Country> getAllCountries() throws Exception {
		return countryDao.findAll();
	}

	@Override
	public Country getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return countryDao.findById(id).orElseThrow(()->new Exception());
	}

	@Override
	public Country addCountry(Country country) throws Exception {
		// TODO Auto-generated method stub
		return countryDao.save(country);
	}

	@Override
	public Country editCountry(Country country) throws Exception {
		// TODO Auto-generated method stub
		return countryDao.save(country);
	}

	@Override
	public void deleteCountry(int id) throws Exception {
		countryDao.deleteById(id);

	}

	@Override
	public Country findCountryByName(String countryName) throws Exception {
		// TODO Auto-generated method stub
		return countryDao.findByName(countryName);
	}

}
