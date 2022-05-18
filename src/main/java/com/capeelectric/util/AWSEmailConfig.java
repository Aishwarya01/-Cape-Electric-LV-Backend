package com.capeelectric.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSEmailConfig {
	
	
	@Value("${app.email.user}")
	private String SMTP_AUTH_USER;	
	
	@Value("${app.email.password}")
	private String SMTP_AUTH_PWD;	
	
	public String getSMTP_AUTH_USER() {
		return SMTP_AUTH_USER;
	}

	public String getSMTP_AUTH_PWD() {
		return SMTP_AUTH_PWD;
	}
}
