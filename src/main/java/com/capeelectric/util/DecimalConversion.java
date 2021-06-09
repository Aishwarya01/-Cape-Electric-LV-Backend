package com.capeelectric.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capeelectric.exception.PeriodicTestingException;


/**
 * This DecimalConversion Util class doing StringInt value convert to StringDecimal
 * @author capeelectricsoftware
 *
 */
public class DecimalConversion {

	private static final Logger logger = LoggerFactory.getLogger(DecimalConversion.class);

	/**
	 * @param String value,decimalSize
	 * convertToDecimal method to StringInt value convert to StringDecimal
	 * @return string
	*/
	public static String convertToDecimal(String value, String decimalSize) throws PeriodicTestingException {

		if (value != null && !value.isEmpty()) {
			logger.info("started DecimalConversion process");
			return String.format(decimalSize, Double.parseDouble(value));

		} else {
			logger.info("throwing Exception for DecimalConversion process");
			throw new PeriodicTestingException("invalid input of value for DecimalConversion");
		}

	}

}
