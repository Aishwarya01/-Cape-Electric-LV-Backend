package com.capeelectric.util;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.service.impl.AWSEmailService;

@Component
public class SendReplyComments {

	@Autowired
	private AWSEmailService awsEmailService;

	@Autowired
	private RegistrationRepository registrationRepo;

	public void sendComments(String userName) throws RegistrationException, Exception {
		Optional<Register> registerRepo = registrationRepo.findByUsername(userName);
		
		if (registerRepo.isPresent() && registerRepo.get().getAssignedBy() !=null) {

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(registerRepo.get().getRegisterId()).toUri();
			String resetUrl = Utility.getSiteURL(uri.toURL());
			awsEmailService.sendEmail(registerRepo.get().getAssignedBy(), userName,
					Constants.EMAIL_SEND_COMMENT_MSG + "\n" + "\n"
							+ (resetUrl.contains("localhost:5000")
									? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
									: "https://www.rushforsafety.com")
							+ "/login");
		} else {
			throw new Exception("Email Id doesn't exist!");
		}
	}

	public void replyComments(String inspectoUserName, String ViewerUserName) throws RegistrationException, Exception {

		Optional<Register> registerRepo = registrationRepo.findByUsername(inspectoUserName);
		if (registerRepo.isPresent() && registerRepo.get().getUsername().equalsIgnoreCase(inspectoUserName)) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(registerRepo.get().getRegisterId()).toUri();
			String resetUrl = Utility.getSiteURL(uri.toURL());
			awsEmailService.sendEmail(ViewerUserName, ViewerUserName,
					Constants.EMAIL_REPLY_COMMENT_MSG + "\n" + "\n"
							+ (resetUrl.contains("localhost:5000")
									? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
									: "https://www.rushforsafety.com")
							+ "/login");
		} else {
			throw new Exception("Given Inspector UserName MisMatched");
		}
	}
	
	public void approveComments(String userName, String approveOrReject) throws RegistrationException, Exception {
		Optional<Register> registerRepo = registrationRepo.findByUsername(userName);

		if (registerRepo.isPresent() && registerRepo.get().getAssignedBy() != null) {

			if (approveOrReject.equalsIgnoreCase("APPROVED")) {
				awsEmailService.sendEmail(registerRepo.get().getAssignedBy(), userName, Constants.EMAIL_APPROVE_COMMENT_MSG);
			} else {
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(registerRepo.get().getRegisterId()).toUri();
				String resetUrl = Utility.getSiteURL(uri.toURL());
				awsEmailService.sendEmail(registerRepo.get().getAssignedBy(), userName,
						Constants.EMAIL_REJECT_COMMENT_MSG + "\n"
								+ (resetUrl.contains("localhost:5000")
										? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
										: "https://www.rushforsafety.com")
								+ "/login");
			}

		} else {
			throw new Exception("Email Id doesn't exist!");
		}
	}
}
