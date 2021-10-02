package com.capeelectric.util;

/**
 * @author capeelectricsoftware
 *
 */
public class Constants {
	
	//RegistrationServiceImpl
	public static final String before_Approve_Permission = "NOT_AUTHORIZED";
	

	//SupplyCharacteristics
	public static final String supplyMainNominalCurrent = "0.00";

	public static final String supplyMainNominalFrequency = "0.00";

	public static final String supplyMainNominalVoltage = "0.00";

	public static final String supplyMainLoopImpedance = "0.000";

	public static final String supplyNominalFrequency = "0.00";

	public static final String supplyNominalVoltage = "0.00";

	public static final String supplyFaultCurrent = "0.00";

	public static final String supplyLoopImpedance = "0.000";
	
	public static final String supply_MainNominal_Current = "Supply_MainNominalCurrent";
	
	public static final String supply_MainNominal_Frequency = "Supply_MainNominalFrequency";
	
	public static final String supply_MainNominal_Voltage = "Supply_MainNominalVoltage";
	
	public static final String supply_MainLoop_Impedance = "Supply_MainLoopImpedance";
	
	public static final String supply_Nominal_Frequency = "Supply_NominalFrequency";
	
	public static final String supply_Nominal_Voltage = "Supply_NominalVoltage";
	
	public static final String supply_Fault_Current = "Supply_FaultCurrent";
	
	public static final String supply_LoopImpedance = "Supply_LoopImpedance";
	

	//Testing
	public static final String testIncomingLoopimpedance = "0.000";

	public static final String testShortCircuit = "0.00";

	public static final String testInstantaneousDmt = "0.00";

	public static final String testInsulationResistance = "0.00";

	public static final String testVoltage = "0.00";

	public static final String loopImpedance = "0.00";

	public static final String faultCurrent = "0.00";
	
	
	public static final String test_Incoming_LoopImpedance = "IncomingLoopImpedance";
	
	public static final String test_Short_CircuitSetting = "ShortCircuitSetting";
	
	public static final String test_EFSetting = "EFSetting";
	
	public static final String test_Insulation_Resistance = "InsulationResistance";
	
	public static final String test_Voltage = "Voltage";
	
	public static final String test_Loopimpedance = "Loopimpedance";
	
	public static final String test_Faultcurrent = "Faultcurrent";

	
	//Comments
	public static final String SEND_COMMENT = "SEND";
	
	public static final String REPLY_COMMENT = "REPLY";
	
	public static final String APPROVE_REJECT_COMMENT = "APPROVED";
	
	public static final String INTIAL_FLAG_VALUE = "0";
	
	public static final String INCREASED_FLAG_VALUE = "1";
	
	//EMAIL SUbject and URL
	public static final String EMAIL_SUBJECT_REGISTRATION = "Your request for accessing the Rush App is approved and you can generate OTP with this link";
	
	public static final String EMAIL_SUBJECT_URL_AWS = "https://www.rushforsafety.com";
	
	public static final String EMAIL_SUBJECT_ADMIN_URL_AWS = "https://admin.rushforsafety.com";
	
	public static final int LIST_ZERO = 0;
	
	public static final String EMAIL_SEND_COMMENT_MSG = "The Viewer has provided some comments/suggestion. You may need to login to check on the comments.";
	
	public static final String EMAIL_REPLY_COMMENT_MSG = "The Inspector has replied Your comments/suggestion. You may need to login to check on the comments.";
	
	public static final String EMAIL_APPROVE_COMMENT_MSG = "Your response to comments is satisfactory Approved.";
	
	public static final String EMAIL_REJECT_COMMENT_MSG = "Your response to comments was not satisfactory. Please provide valid response. \\n \\nYou may need to login to check on the comments.";

	public static final String NUMBER_OF_LICENSES = String.valueOf(5);

	public static final String FROM_EMAIL = "info@rushforsafety.com";
	
	public static final String AWS_EMAIL_PORT = String.valueOf(587);
}
