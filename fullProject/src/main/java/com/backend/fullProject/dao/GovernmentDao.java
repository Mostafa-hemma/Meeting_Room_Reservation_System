package com.backend.fullProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.fullProject.entity.Government;
@Repository
public interface GovernmentDao extends JpaRepository<Government, Integer> {
	public Government findByName(String govName);
	public List<Government> findByCountryId(int countryId);
}
