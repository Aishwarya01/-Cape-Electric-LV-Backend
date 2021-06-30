package com.capeelectric.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.util.TwilioProperties;
import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {

	private final TwilioProperties twilioproperties;

	@Autowired
	public TwilioInitializer(TwilioProperties twilioproperties) {
		this.twilioproperties = twilioproperties;
		Twilio.init(twilioproperties.getAccountSid(), twilioproperties.getAuthToken());
	}

	public TwilioProperties getTwilioproperties() {
		return twilioproperties;
	}
}
