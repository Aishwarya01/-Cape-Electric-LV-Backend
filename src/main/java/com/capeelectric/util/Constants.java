package com.capeelectric.util;

/**
 * @author capeelectricsoftware
 *
 */
public class Constants {

	// E-mail
	public static final String SMTP_HOST_NAME = "email-smtp.ap-south-1.amazonaws.com";

	public static final String SMTP_AUTH_USER = "AKIAVDEKWXGTG5OIL3UY";

	public static final String SMTP_AUTH_PWD = "BGXoPC/S8KJUMcYLVi+SCY5pev0g3Drc5KaVMOtWBtnm";

	public static final String SMTP_HOST_PORT = "587";

	public static final String EMAIL_FROM = "info@rushforsafety.com";

	public static final String FIRST_PERSON_EMAIL = "sd@capeindia.net";

	public static final String SECOND_PERSON_EMAIL = "arunkumar.k@capeindia.net";

	public static final String THIRD_PERSON_EMAIL = "thirumoorthy@capeindia.net";

	public static final String EMAIL_DISABLE = "Y";

	// Inspector Licence
	public static final String NUM_OF_LICENCE = "5";

	// SMS
	public static final String SMS_SEND_OTP = "https://api.backend.rushforsafety.com/api/v1/sendOtp/";

	public static final String SMS_VERIFY_OTP = "https://api.backend.rushforsafety.com/api/v1/verifyOtp/";

	// JWT secret_key
	public static final String JWT_SECRET_KEY = "CapeElectricSoftwareDivision";

	// Comments Messages
	public static final String SEND_COMMENT_MSG = "The Viewer has provided some comments/suggestion. You may need to login to check on the comments.";

	public static final String REPLY_COMMENT_MSG = "The Inspector has replied Your comments/suggestion. You may need to login to check on the comments.";

	public static final String APPROVE_COMMENT_MSG = "Your response to comments is satisfactory Approved.";

	public static final String REJECT_COMMENT_MSG = "Your response to comments was not satisfactory. Please provide valid response. \n \nYou may need to login to check on the comments.";

	// SupplyCharacteristics Decimal points
	public static final String supplyMainNominalCurrent = "0.00";

	public static final String supplyMainNominalFrequency = "0.00";

	public static final String supplyMainNominalVoltage = "0.00";

	public static final String supplyMainLoopImpedance = "0.000";

	public static final String supplyNominalFrequency = "0.00";

	public static final String supplyNominalVoltage = "0.00";

	public static final String supplyFaultCurrent = "0.00";

	public static final String supplyLoopImpedance = "0.000";

	// Testing Decimal points
	public static final String testIncomingLoopimpedance = "0.000";

	public static final String testShortCircuit = "0.00";

	public static final String testInstantaneousDmt = "0.00";

	public static final String testInsulationResistance = "0.00";

	public static final String testVoltage = "0.00";

	public static final String loopImpedance = "0.00";

	public static final String faultCurrent = "0.00";
}
