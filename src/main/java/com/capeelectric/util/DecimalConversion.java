package com.capeelectric.util;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

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
	
	/**
	 * @param String value,decimalSize convertToDecimal method to StringInt value
	 *               convert to StringDecimal
	 * @return string
	 */
	public static String convertToDecimal(String value, String type) throws DecimalConversionException {
		DecimalConversion conversion = new DecimalConversion();
		DecimalFormat decimalSize = conversion.findDecimalSize(type);
		String nominalValues = "";
		String decimalValue = "NA";
		if (value != null && !value.isEmpty()) {
			StringTokenizer stringTokenizer = new StringTokenizer(value, ",");

			logger.info("started DecimalConversion process");
			while (stringTokenizer.hasMoreElements()) {
				String token = stringTokenizer.nextToken();
				if (token.equalsIgnoreCase("NA")) {
					nominalValues = nominalValues.concat("NA").concat(",");
				} else {
					decimalValue = decimalSize.format(Double.parseDouble(token));
					nominalValues = nominalValues.concat(decimalValue).concat(",");
				}

			}
		} else {
			logger.info("failed DecimalConversion process");
			throw new DecimalConversionException("invalid input of value for DecimalConversion");

		}

		logger.info("ended DecimalConversion process");
		return nominalValues.substring(0, nominalValues.length() - 1);
	}

	private DecimalFormat findDecimalSize(String type) throws DecimalConversionException {
		if (type != null) {
			if (type.equalsIgnoreCase("Supply_MainNominalCurrent")) {
				return new DecimalFormat(DecimalConversionConstant.supplyMainNominalCurrent);
			} else if (type != null && type.equalsIgnoreCase("Supply_MainNominalFrequency")) {
				return new DecimalFormat(DecimalConversionConstant.supplyMainNominalFrequency);
			} else if (type != null && type.equalsIgnoreCase("Supply_MainNominalVoltage")) {
				return new DecimalFormat(DecimalConversionConstant.supplyMainNominalVoltage);
			} else if (type != null && type.equalsIgnoreCase("Supply_MainLoopImpedance")) {
				return new DecimalFormat(DecimalConversionConstant.supplyMainLoopImpedance);
			} else if (type != null && type.equalsIgnoreCase("Supply_NominalFrequency")) {
				return new DecimalFormat(DecimalConversionConstant.supplyNominalFrequency);
			} else if (type != null && type.equalsIgnoreCase("Supply_NominalVoltage")) {
				return new DecimalFormat(DecimalConversionConstant.supplyNominalVoltage);
			} else if (type != null && type.equalsIgnoreCase("Supply_FaultCurrent")) {
				return new DecimalFormat(DecimalConversionConstant.supplyFaultCurrent);
			} else if (type != null && type.equalsIgnoreCase("Supply_LoopImpedance")) {
				return new DecimalFormat(DecimalConversionConstant.supplyLoopImpedance);
			} else if (type != null && type.equalsIgnoreCase("IncomingLoopImpedance")) {
				return new DecimalFormat(DecimalConversionConstant.testIncomingLoopimpedance);
			} else if (type != null && type.equalsIgnoreCase("ShortCircuitSetting")) {
				return new DecimalFormat(DecimalConversionConstant.testShortCircuit);
			} else if (type != null && type.equalsIgnoreCase("EFSetting")) {
				return new DecimalFormat(DecimalConversionConstant.testInstantaneousDmt);
			} else if (type != null && type.equalsIgnoreCase("InsulationResistance")) {
				return new DecimalFormat(DecimalConversionConstant.testInsulationResistance);
			} else if (type.equalsIgnoreCase("Voltage")) {
				return new DecimalFormat(DecimalConversionConstant.testVoltage);
			} else if (type != null && type.equalsIgnoreCase("Loopimpedance")) {
				return new DecimalFormat(DecimalConversionConstant.loopImpedance);
			} else if (type != null && type.equalsIgnoreCase("Faultcurrent")) {
				return new DecimalFormat(DecimalConversionConstant.faultCurrent);
			}
		} else {
			throw new DecimalConversionException("Finding DecimalConversion variable type failed");
		}
		return null;
	}
}
