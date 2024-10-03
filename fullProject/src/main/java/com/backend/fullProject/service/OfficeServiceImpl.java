package com.backend.fullProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.fullProject.dao.OfficeDao;
import com.backend.fullProject.entity.Government;
import com.backend.fullProject.entity.Office;
@Service
public class OfficeServiceImpl implements OfficeService {
	
	@Autowired
	private OfficeDao officeDao;
	
	@Autowired
	private GovernmentService govService;

	@Override
	public List<Office> getAll() throws Exception {
		// TODO Auto-generated method stub
		return officeDao.findAll();
	}

	@Override
	public Office getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return officeDao.findById(id).orElseThrow(()-> new Exception());
	}

	@Override
	public Office addOffice(Office myOffice) throws Exception {

//		Government myGov=govService.findByGovName(govName);
//		myOffice.setGovernment(myGov);
//		myGov.getOffcies().add(myOffice);
//		govService.updateGov(myGov);
		return officeDao.save(myOffice);
	}

	@Override
	public Office editOffice(Office myOffice) throws Exception {
		// TODO Auto-generated method stub
		return officeDao.save(myOffice);
	}

	@Override
	public Office getOfficeByName(String officeName) throws Exception {
		// TODO Auto-generated method stub
		return officeDao.findByName(officeName);
	}

	@Override
	public List<Office> getByGovId(int govId) throws Exception {
		// TODO Auto-generated method stub
		return officeDao.findByGovernmentId(govId);
	}

}
