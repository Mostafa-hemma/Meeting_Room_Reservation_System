package com.backend.fullProject.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blacklistedtoken")
public class BlackListedToken {

	@Id
	@Column
	private String token;
	@Column
	private LocalDateTime expiration;
	
	public BlackListedToken() {}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}
	
	
	
}
