package com.backend.fullProject.service;

import java.time.LocalDateTime;

public interface BlackListedService {

	public void blacklistToken(String token, LocalDateTime expiration);
	 public boolean isTokenBlacklisted(String token);
}
