package com.capeelectric.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.service.SendSmsEmailService;
import com.capeelectric.service.impl.EmailService;
import com.capeelectric.util.VerificationResult;

@RestController
@RequestMapping("/api/v1")
public class SendSmsEmailController {

	@Autowired
	private SendSmsEmailService sendSmsEmailService;

	@Autowired
	private EmailService emailService;

	@GetMapping("/sendOtp/{phone}")
	public ResponseEntity<String> sendotp(@PathVariable String phone) {
		VerificationResult result = sendSmsEmailService.startVerification(phone);
		if (result.isValid()) {
			return new ResponseEntity<>("Otp Sent..", HttpStatus.OK);
		}
		return new ResponseEntity<>("Otp failed to sent..", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/verifyOtp/{phone}/{otp}")
	public ResponseEntity<String> sendotp(@PathVariable String phone, @PathVariable String otp) {

		VerificationResult result = sendSmsEmailService.checkverification(phone, otp);
		if (result.isValid()) {
			return new ResponseEntity<>("Your number is Verified", HttpStatus.OK);
		}
		return new ResponseEntity<>("Something wrong/ Otp incorrect", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/verifyEmail/{email}")
	public ResponseEntity<String> verifyEmail(@PathVariable String email) throws IOException {
		String subject = "your account sucessFully Created";
		emailService.sendEmail(email, subject);

		return new ResponseEntity<>("Your email sucessfully sent", HttpStatus.OK);
	}
}
