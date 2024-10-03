package com.backend.fullProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.fullProject.entity.Office;
@Repository
public interface OfficeDao extends JpaRepository<Office, Integer> {
	public Office findByName(String officeName);
//	@Query("SELECT o.name as officeName, g.name as governmentName, c.name as countryName FROM Office o JOIN o.government g JOIN g.country c WHERE o.id = :officeId")
//	    List<String> findOfficeGovCountryNamesByOfficeId(@Param("officeId") int officeId);

	public List<Office> findByGovernmentId(int govId);
}
