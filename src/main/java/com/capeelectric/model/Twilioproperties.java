package com.capeelectric.model;

 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class Twilioproperties {
	private String accountSid = "AC809d14ac33956b12d4d93cf87e46e270";
	private String authToken= "ecdba080d0c76248e9bac0a236055968";
	private String serviceId ="ZS855bfd9b7d8059c94300cebbc7ce4ce2";
	
	   
	public Twilioproperties() {
		
	}
	public String getAccountSid() {
		return "AC809d14ac33956b12d4d93cf87e46e270";
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return "ecdba080d0c76248e9bac0a236055968";
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getServiceId() {
		return "VAad11230d0fdde552e189f269e9030977";
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	


}
