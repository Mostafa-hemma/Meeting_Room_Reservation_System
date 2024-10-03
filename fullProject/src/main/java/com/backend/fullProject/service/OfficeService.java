package com.backend.fullProject.service;

import java.util.List;

import com.backend.fullProject.entity.Office;

public interface OfficeService {
	
	public List<Office> getAll()throws Exception;
	public Office getById(int id)throws Exception;
	public Office addOffice(Office myOffice)throws Exception;
	public Office editOffice(Office myOffice)throws Exception;
	public Office getOfficeByName(String officeName)throws Exception;
	public List<Office> getByGovId(int govId)throws Exception;

}
