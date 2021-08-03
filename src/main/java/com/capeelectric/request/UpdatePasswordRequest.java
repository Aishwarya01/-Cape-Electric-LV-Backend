package com.capeelectric.request;
/**
 * 
 * @author capeelectricsoftware
 *
 */
public class UpdatePasswordRequest extends AuthenticationRequest {
	private Integer otp;
	public UpdatePasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdatePasswordRequest(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}

	public UpdatePasswordRequest(Integer otp) {
		super();
		this.otp = otp;
	}

	public Integer getOtp() {
		return otp;
	}


	public void setOtp(Integer otp) {
		this.otp = otp;
	}
}
