package com.capeelectric.util;

import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capeelectric.exception.DecimalConversionException;

/**
 * This DecimalConversion Util class doing StringInt value convert to
 * StringDecimal
 * 
 * @author capeelectricsoftware
 *
 */
public class DecimalConversion {

	private static final Logger logger = LoggerFactory.getLogger(DecimalConversion.class);

	private static String decimalValues = "";

	/**
	 * @param String value,decimalSize convertToDecimal method to StringInt value
	 *               convert to StringDecimal
	 * @return string
	 */
	public static String convertToDecimal(String value, String type)  {
		try {
			DecimalConversion conversion = new DecimalConversion();
			String nominalValues = "";
			DecimalFormat decimalSize;
			decimalSize = conversion.findDecimalSize(type);
			if(type.equalsIgnoreCase(Constants.supply_MainNominal_Frequency) || type.equalsIgnoreCase(Constants.supply_Nominal_Frequency)) {
				return (value.equalsIgnoreCase("NA") ? value : decimalSize.format(Double.parseDouble(value)));
			}else {
				String decimalValue = "NA";
				if (value != null && !value.isEmpty()) {
					StringTokenizer stringTokenizer = new StringTokenizer(value, ",");

					logger.info("started DecimalConversion process");
					while (stringTokenizer.hasMoreElements()) {
						String token = stringTokenizer.nextToken();
						if (token.equalsIgnoreCase("NA")) {
							nominalValues = nominalValues.concat("NA").concat(",");
						} else {
							if (isDecimalNum(token, decimalSize)) {
								nominalValues = nominalValues.concat(decimalValues).concat(",");
							}
							else {
								decimalValue = decimalSize.format(Double.parseDouble(token));
								nominalValues = nominalValues.concat(decimalValue).concat(",");
							}
						}

					}
				} else {
					logger.info("failed DecimalConversion process");
					throw new DecimalConversionException("invalid input of value for DecimalConversion");
				}
			}
			logger.info("ended DecimalConversion process");
			return nominalValues.substring(0, nominalValues.length() - 1);
		} catch (DecimalConversionException e) {
			e.setMessage(type);
			logger.error(type);
		}
		return "";
	}

	private DecimalFormat findDecimalSize(String type) throws DecimalConversionException {
		if (type != null) {
			if (type.equalsIgnoreCase(Constants.supply_MainNominal_Current)) {
				return new DecimalFormat(Constants.supplyMainNominalCurrent);
			} else if (type != null && type.equalsIgnoreCase(Constants.supply_MainNominal_Frequency)) {
				return new DecimalFormat(Constants.supplyMainNominalFrequency);
			} else if (type != null && type.equalsIgnoreCase(Constants.supply_MainNominal_Voltage)) {
				return new DecimalFormat(Constants.supplyMainNominalVoltage);
			} else if (type != null && type.equalsIgnoreCase(Constants.supply_MainLoop_Impedance)) {
				return new DecimalFormat(Constants.supplyMainLoopImpedance);
			} else if (type != null && type.equalsIgnoreCase(Constants.supply_Nominal_Frequency)) {
				return new DecimalFormat(Constants.supplyNominalFrequency);
			} else if (type != null && type.equalsIgnoreCase(Constants.supply_Nominal_Voltage)) {
				return new DecimalFormat(Constants.supplyNominalVoltage);
			} else if (type != null && type.equalsIgnoreCase(Constants.supply_Fault_Current)) {
				return new DecimalFormat(Constants.supplyFaultCurrent);
			} else if (type != null && type.equalsIgnoreCase(Constants.supply_LoopImpedance)) {
				return new DecimalFormat(Constants.supplyLoopImpedance);
			} else if (type != null && type.equalsIgnoreCase(Constants.test_Incoming_LoopImpedance)) {
				return new DecimalFormat(Constants.testIncomingLoopimpedance);
			} else if (type != null && type.equalsIgnoreCase(Constants.test_Short_CircuitSetting)) {
				return new DecimalFormat(Constants.testShortCircuit);
			} else if (type != null && type.equalsIgnoreCase(Constants.test_EFSetting)) {
				return new DecimalFormat(Constants.testInstantaneousDmt);
			} else if (type != null && type.equalsIgnoreCase(Constants.test_Insulation_Resistance)) {
				return new DecimalFormat(Constants.testInsulationResistance);
			} else if (type.equalsIgnoreCase(Constants.test_Voltage)) {
				return new DecimalFormat(Constants.testVoltage);
			} else if (type != null && type.equalsIgnoreCase(Constants.test_Loopimpedance)) {
				return new DecimalFormat(Constants.loopImpedance);
			} else if (type != null && type.equalsIgnoreCase(Constants.test_Faultcurrent)) {
				return new DecimalFormat(Constants.faultCurrent);
			}
		} else {
			throw new DecimalConversionException("Finding DecimalConversion variable type failed");
		}
		return null;
	}

	private static boolean isDecimalNum(String token,DecimalFormat decimalSize) {
		boolean flag = false;
		if (Pattern.matches("([0-9]*)\\.([0-9]*)", token)) {
			if (token.split("\\.")[1].length() == decimalSize.getMaximumFractionDigits()) {
				decimalValues = token;
				flag = true;
			} else if (token.split("\\.")[1].length() > decimalSize.getMaximumFractionDigits()) {
				decimalValues = String.format("%." + decimalSize.getMaximumFractionDigits() + "f",
						Double.parseDouble(token));
				flag = true;
			} else if (token.split("\\.")[1].length() < decimalSize.getMaximumFractionDigits()) {
				flag = false;
			}
		}
		return flag;
	}
}
