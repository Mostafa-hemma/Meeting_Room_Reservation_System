package com.backend.fullProject.service;

import java.util.List;

import com.backend.fullProject.entity.Government;

public interface GovernmentService {
	
	public List<Government> getAllGov() throws Exception;
	public Government getById(int id) throws Exception;
	public Government addGov(Government myGov) throws Exception;
	public Government updateGov(Government myGov) throws Exception;
	public Government findByGovName(String govName)throws Exception;
	public List<Government> findByCountryId(int countryId)throws Exception;
}
