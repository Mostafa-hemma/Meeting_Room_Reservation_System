package com.backend.fullProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.fullProject.entity.Country;
@Repository
public interface CountryDao extends JpaRepository<Country, Integer> {
	
	public Country findByName(String countryName);

}
