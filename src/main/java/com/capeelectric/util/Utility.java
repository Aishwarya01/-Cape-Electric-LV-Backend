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

}