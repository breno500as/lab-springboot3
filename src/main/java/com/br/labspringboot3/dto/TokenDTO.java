package com.br.labspringboot3.dto;

import java.io.Serializable;
import java.util.Date;

public class TokenDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7896892931911019730L;

	private String username;

	private Boolean authenticated;

	private Date created;

	private Date expiration;

	private String token;

	private String refreshToken;
	
	public TokenDTO() {
		
	}

	public TokenDTO(String username, Boolean authenticated, Date created, Date expiration, String token,
			String refreshToken) {
		super();
		this.username = username;
		this.authenticated = authenticated;
		this.created = created;
		this.expiration = expiration;
		this.token = token;
		this.refreshToken = refreshToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
