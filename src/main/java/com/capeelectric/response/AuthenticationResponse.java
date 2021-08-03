package com.capeelectric.response;

import java.io.Serializable;

import com.capeelectric.model.Admin;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final Admin admin;
	public AuthenticationResponse(String jwttoken, Admin admin) {
		this.jwttoken = jwttoken;
		this.admin = admin;
	}
	public String getToken() {
		return this.jwttoken;
	}
	public Admin getUsers() {
		return admin;
	}
	
}