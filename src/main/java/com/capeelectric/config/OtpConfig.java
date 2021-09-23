package com.capeelectric.config;

import org.springframework.stereotype.Component;

import com.capeelectric.util.Constants;

@Component
public class OtpConfig {
	
	private String sendOtp =Constants.SMS_SEND_OTP;
	
	private String verifyOtp=Constants.SMS_VERIFY_OTP;

	public String getSendOtp() {
		return sendOtp;
	}

	public void setSendOtp(String sendOtp) {
		this.sendOtp = sendOtp;
	}

	public String getVerifyOtp() {
		return verifyOtp;
	}

	public void setVerifyOtp(String verifyOtp) {
		this.verifyOtp = verifyOtp;
	}
	
	

}
