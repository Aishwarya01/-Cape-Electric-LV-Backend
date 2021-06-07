package com.capeelectric.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class EmailService {

	@Value("${app.sendgrid.from.email}")
	private String fromEmail;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	SendGrid sendGrid;
	/**
	 * Send Email 
	 * @param email
	 * @return
	 * @throws IOException 
	 */
	public void sendEmail(String email, String subject) throws IOException  {

		Email from = new Email(fromEmail);
	    Email to = new Email(email);
	    Content content = new Content("text/plain", subject);
	    Mail mail = new Mail(from, "Welcome to Rush App", to, content);
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sendGrid.api(request);
	      logger.debug("Response object from sendGrid api", response.getStatusCode());
	    } catch (IOException ex) {
	      throw ex;
	    }
	}
}