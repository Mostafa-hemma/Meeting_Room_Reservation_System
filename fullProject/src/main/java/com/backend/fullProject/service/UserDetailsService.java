package com.backend.fullProject.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.fullProject.dao.EmployeeDao;
import com.backend.fullProject.entity.Employee;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee myEmployee = employeeDao.findByEmail(email);

		return new org.springframework.security.core.userdetails.User(String.valueOf(myEmployee.getEmail()),
				myEmployee.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(myEmployee.getRole())));
//	        	return new org.springframework.security.core.userdetails.User(String.valueOf(user.getPhoneNumber()), user.getPassword(), 
//	                    user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//	        

	}

}