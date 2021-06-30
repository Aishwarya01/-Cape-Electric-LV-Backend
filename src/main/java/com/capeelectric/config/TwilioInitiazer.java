package com.capeelectric.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.util.TwilioProperties;
import com.twilio.Twilio;

@Configuration
public class TwilioInitiazer {

	private final TwilioProperties twilioproperties;

	@Autowired
	public TwilioInitiazer(TwilioProperties twilioproperties) {
		this.twilioproperties = twilioproperties;
		Twilio.init(twilioproperties.getAccountSid(), twilioproperties.getAuthToken());
		System.out.println("Twilio initialized with account-" + twilioproperties.getAccountSid());
	}
}
