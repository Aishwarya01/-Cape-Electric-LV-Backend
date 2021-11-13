package com.capeelectric.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capeelectric.util.AWSEmailConfig;
import com.capeelectric.util.Constants;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class AWSEmailService {

	private String FROM = Constants.FROM_EMAIL;
	
	@Value("${app.email.disable}")
	private String emailDisable;
	
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
		
		if(!emailDisable.equalsIgnoreCase("Y")) {
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

			message.setSubject(Constants.EMAIL_SUBJECT);
			message.setContent(content, "text/plain");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(FROM));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			transport.connect(emailConfig.getSMTP_HOST_NAME(), Integer.valueOf(Constants.AWS_EMAIL_PORT), emailConfig.getSMTP_AUTH_USER(), emailConfig.getSMTP_AUTH_PWD());
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		}
		


	}
	
	public  void sendEmailToAdmin(String content) throws MessagingException {

		if(!emailDisable.equalsIgnoreCase("Y")) {
			logger.debug("Inside AWS Email");
			final String TO = Constants.FIRST_PERSON_EMAIL+","+Constants.SECOND_PERSON_EMAIL+","+Constants.THIRD_PERSON_EMAIL; // {YOUR_RECIPIENT_EMAIL_ADDRESS}

			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", emailConfig.getSMTP_HOST_NAME());
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.starttls.enable", "true");

			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);

			Transport transport = mailSession.getTransport("smtp");

			MimeMessage message = new MimeMessage(mailSession);

			message.setSubject(Constants.EMAIL_ADMIN_SUBJECT);
			message.setContent(content, "text/plain");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(FROM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO) );
			transport.connect(emailConfig.getSMTP_HOST_NAME(), Integer.valueOf(Constants.AWS_EMAIL_PORT), emailConfig.getSMTP_AUTH_USER(), emailConfig.getSMTP_AUTH_PWD());
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		}
		


	}
	
	/**
	 * Send Email 
	 * @param email
	 * @return
	 * @throws IOException 
	 */
	public  void sendEmail(String toEmail,String ccEmail, String content) throws MessagingException {
		
		if(!emailDisable.equalsIgnoreCase("Y")) {
		logger.debug("Inside AWS Email");
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", emailConfig.getSMTP_HOST_NAME());
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "true");

		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);

		Transport transport = mailSession.getTransport("smtp");

		MimeMessage message = new MimeMessage(mailSession);

		message.setSubject(Constants.EMAIL_SUBJECT);
		message.setContent(content, "text/plain");
		message.setSentDate(new Date());
		message.setFrom(new InternetAddress(FROM));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccEmail));
		transport.connect(emailConfig.getSMTP_HOST_NAME(), Integer.valueOf(Constants.AWS_EMAIL_PORT), emailConfig.getSMTP_AUTH_USER(), emailConfig.getSMTP_AUTH_PWD());
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
	    transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
		transport.close();

		}
	}
	/**
	 * Download and Send the email to the user
	 * @param userName
	 * @throws MessagingException
	 */
	public void sendEmailPDF(String userName) throws MessagingException {
		
		if(!emailDisable.equalsIgnoreCase("Y")) {
			String to = userName;
			String from = FROM;
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", emailConfig.getSMTP_HOST_NAME());
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			Session mailSession = Session.getDefaultInstance(props);

			try {

				Message message = new MimeMessage(mailSession);
				Transport transport = mailSession.getTransport("smtp");
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				message.setSubject(Constants.EMAIL_SUBJECT);
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText("Attached is the final report PDF for your kind reference");
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				messageBodyPart = new MimeBodyPart();
				String filename = ("finalreport.pdf");

				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(filename);
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
				transport.connect(emailConfig.getSMTP_HOST_NAME(), Integer.valueOf(Constants.AWS_EMAIL_PORT), emailConfig.getSMTP_AUTH_USER(), emailConfig.getSMTP_AUTH_PWD());
				transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
				transport.close();
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}