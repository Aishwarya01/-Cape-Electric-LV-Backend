package com.capeelectric.util;

import java.net.URL;
import java.util.Random;
/**
 * 
 * @author capeelectricsoftware
 *
 */
public class Utility {
	public static String getSiteURL(URL request) {
		String siteURL = request.toString();
		return siteURL.replace(request.getPath(), "");
	}

	public static int generateOTP(String key){
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return otp;
	}
}