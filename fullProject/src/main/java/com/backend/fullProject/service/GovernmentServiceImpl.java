package com.backend.fullProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.fullProject.dao.GovernmentDao;
import com.backend.fullProject.entity.Country;
import com.backend.fullProject.entity.Government;
@Service
public class GovernmentServiceImpl implements GovernmentService {
	
	@Autowired
	private GovernmentDao govDao;
	
	@Autowired
	private CountryService countryService;

	@Override
	public List<Government> getAllGov() throws Exception {
		// TODO Auto-generated method stub
		return govDao.findAll();
	}

	@Override
	public Government getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return govDao.findById(id).orElseThrow(()->new Exception());
	}

	@Override
	public Government addGov(Government myGov) throws Exception {

		return govDao.save(myGov);
	}

	@Override
	public Government updateGov(Government myGov) throws Exception {
		// TODO Auto-generated method stub
		return govDao.save(myGov);
	}

	@Override
	public Government findByGovName(String govName) throws Exception {
		// TODO Auto-generated method stub
		return govDao.findByName(govName);
	}

	@Override
	public List<Government> findByCountryId(int countryId) throws Exception {
		// TODO Auto-generated method stub
		return govDao.findByCountryId(countryId);
	}

}
