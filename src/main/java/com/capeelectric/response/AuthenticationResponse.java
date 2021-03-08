package com.capeelectric.response;

import java.io.Serializable;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final UserDetails userDetails;
	public AuthenticationResponse(String jwttoken, UserDetails userDetails) {
		this.jwttoken = jwttoken;
		this.userDetails = userDetails;
	}
	public String getToken() {
		return this.jwttoken;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	
	
}