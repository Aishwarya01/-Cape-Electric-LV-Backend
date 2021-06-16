package com.aws_sns;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class AWS_Email_Notification {

	static final String from = " ";  //from mailId
	static final String to = " ";    //reciver mailId
	
	
 
	public static void main(String[] args) {
		  String accessKey = "AKIAVDEKWXGTKAESOLPD";

		  String secretKey= "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";
		  
		  String subject= "cape electric pvt ltd";
		  
		  String testBody="account created sucessfully ";
		try {
			AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		 
			com.amazonaws.services.simpleemail.AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
					.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1).build();

			 SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(to)).withMessage(new Message().withBody( new Body().
					  withHtml(new Content().withCharset("UTF-8").withData(testBody ))
					 .withText(new Content().withCharset("UTF-8").withData("welcome cape "))).withSubject(new Content().withCharset("UTF-8").withData(subject))).
			 withSource(from);
			 client.sendEmail(request);
 
		} catch (Exception e) {
			System.out.println("failure"+e.getMessage());
		}
	}

}