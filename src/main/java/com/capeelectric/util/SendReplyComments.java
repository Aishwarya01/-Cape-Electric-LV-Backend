package com.capeelectric.util;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.service.impl.AWSEmailService;

@Component
public class SendReplyComments {

	@Value("${email.send.comment.message}")
	private String sendCommentMsg;

	@Value("${email.reply.comment.message}")
	private String replyCommentMsg;
	
	@Value("${email.approve.comment.message}")
	private String approveCommentMsg;
	
	@Value("${email.reject.comment.message}")
	private String rejectCommentMsg;

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
					sendCommentMsg + "\n" + "\n"
							+ (resetUrl.contains("localhost:5000")
									? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
									: "https://www.rushforsafety.com")
							+ "/login");
		} else {
			throw new Exception("Email Id doesn't exist!");
		}
	}

	public void replyComments(String inspectoUserName, String ViewerUserName) throws RegistrationException, Exception {

		Optional<Register> registerRepo = registrationRepo.findByUsername(ViewerUserName);
		if (registerRepo.isPresent() && registerRepo.get().getAssignedBy().equalsIgnoreCase(inspectoUserName)) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(registerRepo.get().getRegisterId()).toUri();
			String resetUrl = Utility.getSiteURL(uri.toURL());
			awsEmailService.sendEmail(ViewerUserName, registerRepo.get().getAssignedBy(),
					replyCommentMsg + "\n" + "\n"
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
				awsEmailService.sendEmail(registerRepo.get().getAssignedBy(), userName, approveCommentMsg);
			} else {
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(registerRepo.get().getRegisterId()).toUri();
				String resetUrl = Utility.getSiteURL(uri.toURL());
				awsEmailService.sendEmail(registerRepo.get().getAssignedBy(), userName,
						rejectCommentMsg + "\n"
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
