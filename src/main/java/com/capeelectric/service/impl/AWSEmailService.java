package com.capeelectric.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capeelectric.util.AWSEmailConfig;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class AWSEmailService {

	@Value("${app.email.from}")
	private String FROM;

	private static final Logger logger = LoggerFactory.getLogger(AWSEmailService.class);

	@Autowired
	private AWSEmailConfig emailConfig;
	/**
	 * Send Email 
	 * @param email
	 * @return
	 * @throws IOException 
	 */
	public  void sendEmail(String email, String content) throws MessagingException {
		
		logger.debug("Inside AWS Email");
		final String TO = email; // {YOUR_RECIPIENT_EMAIL_ADDRESS}

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", emailConfig.getSMTP_HOST_NAME());
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "true");

		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);

		Transport transport = mailSession.getTransport("smtp");

		MimeMessage message = new MimeMessage(mailSession);

		message.setSubject("Welcome to Rush Safety Application");
		message.setContent(content, "text/plain");
		message.setSentDate(new Date());
		message.setFrom(new InternetAddress(FROM));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		transport.connect(emailConfig.getSMTP_HOST_NAME(), Integer.valueOf(emailConfig.getSMTP_HOST_PORT()), emailConfig.getSMTP_AUTH_USER(), emailConfig.getSMTP_AUTH_PWD());
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
		System.out.println("email sent successfully........");


	}
}