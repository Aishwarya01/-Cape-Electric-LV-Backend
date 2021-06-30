package com.capeelectric.service;

import com.capeelectric.util.VerificationResult;

public interface SendSmsEmailService {

	public VerificationResult startVerification(String phone);

	public VerificationResult checkverification(String phone, String code);

}
