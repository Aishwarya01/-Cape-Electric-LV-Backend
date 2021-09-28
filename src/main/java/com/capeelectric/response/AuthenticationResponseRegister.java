package com.capeelectric.response;

import java.io.Serializable;

import com.capeelectric.model.Register;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public class AuthenticationResponseRegister implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final Register register;

	public AuthenticationResponseRegister(String jwttoken, Register register) {
		this.jwttoken = jwttoken;
		this.register = register;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public Register getRegister() {
		return register;
	}

}