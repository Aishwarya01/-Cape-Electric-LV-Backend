package com.capeelectric.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.service.SendSmsService;
import com.capeelectric.util.TwilioProperties;
import com.capeelectric.util.VerificationResult;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.rest.verify.v2.service.VerificationCreator;

@Service
public class SendSmsServiceImpl implements SendSmsService{

	private TwilioProperties twilioproperties;

	@Autowired
	public SendSmsServiceImpl(TwilioProperties twilioproperties) {
		this.twilioproperties=twilioproperties;
	}
	
 	@Override
	public VerificationResult startVerification(String phone) {
		try {

			VerificationCreator creator = Verification.creator(twilioproperties.getServiceId(), phone, "sms");
			Verification verification = creator.create();
			if ("approved".equals(verification.getStatus()) || "pending".equals(verification.getStatus())) {
				return new VerificationResult(verification.getSid());
			}
		} catch (ApiException exception) {
			System.out.println(exception.getMessage());
			return new VerificationResult(new String[] { exception.getMessage() });

		}
		return null;
	}

	@Override
	public VerificationResult checkverification(String phone, String code) {
		try {
			VerificationCheck verification = VerificationCheck.creator(twilioproperties.getServiceId(), code)
					.setTo(phone).create();
			if ("approved".equals(verification.getStatus())) {
				return new VerificationResult(verification.getSid());
			}
			return new VerificationResult(new String[] { "Invalid code." });
		} catch (ApiException exception) {
			return new VerificationResult(new String[] { exception.getMessage() });
		}
	}
	
}
