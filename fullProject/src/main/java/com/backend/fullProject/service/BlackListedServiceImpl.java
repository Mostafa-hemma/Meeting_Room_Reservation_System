package com.backend.fullProject.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.fullProject.dao.BlackListedTokenDao;
import com.backend.fullProject.entity.BlackListedToken;

@Service
public class BlackListedServiceImpl implements BlackListedService {
	
	@Autowired
	private BlackListedTokenDao blackListedTokenDao;

	@Override
	public void blacklistToken(String token, LocalDateTime expiration) {
		BlackListedToken blackListedToken=new BlackListedToken();
		blackListedToken.setToken(token);
		blackListedToken.setExpiration(expiration);
		blackListedTokenDao.save(blackListedToken);
	}

	@Override
	public boolean isTokenBlacklisted(String token) {
		 Optional<BlackListedToken> blacklistedToken = blackListedTokenDao.findByToken(token);
	        return blacklistedToken.isPresent() && blacklistedToken.get().getExpiration().isAfter(LocalDateTime.now());
	}

}
