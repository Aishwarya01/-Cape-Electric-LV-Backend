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
	public static String convertToDecimal(String value, DecimalFormat decimalSize) throws DecimalConversionException {

		String nominalValues = "";
		String decimalValue = "NA";
		if (value !=null && !value.isEmpty()) {
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

}
