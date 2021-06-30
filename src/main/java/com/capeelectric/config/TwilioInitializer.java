package com.capeelectric.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.util.TwilioProperties;
import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {

	private final TwilioProperties twilioProperties;

	@Autowired
	public TwilioInitializer(TwilioProperties twilioProperties) {
		this.twilioProperties = twilioProperties;
		Twilio.init(twilioProperties.getAccountSid(), twilioProperties.getAuthToken());
	}

	public TwilioProperties getTwilioProperties() {
		return twilioProperties;
	}
}
