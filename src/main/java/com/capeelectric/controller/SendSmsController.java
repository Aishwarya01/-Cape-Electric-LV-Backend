package com.capeelectric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.service.SendSmsService;
import com.capeelectric.util.VerificationResult;

@RestController
@RequestMapping("/api/v1")
public class SendSmsController {

	@Autowired
	private SendSmsService sendSmsService;

	@GetMapping("/sendOtp/{phone}")
	public ResponseEntity<String> sendOtp(@PathVariable String phone) {
		VerificationResult result = sendSmsService.startVerification(phone);
		if (result.isValid()) {
			return new ResponseEntity<>("Otp Sent..", HttpStatus.OK);
		}
		return new ResponseEntity<>("Otp failed to sent..", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/verifyOtp/{phone}/{otp}")
	public ResponseEntity<String> verifyOtp(@PathVariable String phone, @PathVariable String otp) {

		VerificationResult result = sendSmsService.checkverification(phone, otp);
		if (result.isValid()) {
			return new ResponseEntity<>("Your number is Verified", HttpStatus.OK);
		}
		return new ResponseEntity<>("Something wrong/ Otp incorrect", HttpStatus.BAD_REQUEST);
	}

}
