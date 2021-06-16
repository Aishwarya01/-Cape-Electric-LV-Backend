package com.aws_sns;

import java.util.HashMap;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

 
public class App 
{
	
	public static final String AWS_ACCESS_KEY = "aws.accesskeyId";
	
	public static final String AWS_SECRET_KEY = "aws.secretkey";
	
	static {
		System.setProperty( AWS_ACCESS_KEY,"AKIAVDEKWXGTKAESOLPD");
		System.setProperty(AWS_SECRET_KEY, "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");
	}
	public void send(String msg, String phoneNumber) {
	
		AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.AP_SOUTH_1).build();
		HashMap<String, MessageAttributeValue> smsAttributes = new HashMap<String , MessageAttributeValue>();
		smsAttributes.put("AWS.SNS.SMS.SenderID",
				new MessageAttributeValue().withStringValue("SENDOTP").withDataType("String")); // Topic value of AWS_topic
		
		smsAttributes.put("AWS.SNS.SMS.SMSType" , new MessageAttributeValue()
				.withStringValue("Transactional").withDataType("String"));
		
		// take the arn value form aws_topic
		PublishResult result = snsClient.publish(new PublishRequest()
				.withTopicArn
				("arn:aws:sns:ap-south-1:350330730918:SENDOTP").withMessage(msg)
				 
				.withMessageAttributes(smsAttributes));
 		
		
	}

	public static void main(String[] args) {
		int min = 100000;  
        int max = 999999; 
        int number=(int)(Math.random()*(max-min+1)+min);
        
		App app = new App();
		app.send("This is your One Time Password - "+number,  "+91xxxxxxx");
	}
}
