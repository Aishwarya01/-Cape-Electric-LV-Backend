package com.capeelectric.util;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSEmailConfig {
	
	private String SMTP_HOST_NAME = Constants.SMTP_HOST_NAME;

	private String SMTP_AUTH_USER = Constants.SMTP_AUTH_USER;

	private String SMTP_AUTH_PWD = Constants.SMTP_AUTH_PWD;

	private String SMTP_HOST_PORT = Constants.SMTP_HOST_PORT;

	public String getSMTP_HOST_NAME() {
		return SMTP_HOST_NAME;
	}

	public String getSMTP_AUTH_USER() {
		return SMTP_AUTH_USER;
	}

	public String getSMTP_AUTH_PWD() {
		return SMTP_AUTH_PWD;
	}

	public String getSMTP_HOST_PORT() {
		return SMTP_HOST_PORT;
	}
	
	
	
}
