package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String first_name() {
		String generateString = RandomStringUtils.randomAlphabetic(1);
		return (""+generateString);
	}
	
	public static String last_name() {
		String generateString = RandomStringUtils.randomAlphabetic(1);
		return (""+generateString);
	}
	
	public static String email() {
		
		String generatedString = RandomStringUtils.randomAlphanumeric(1);
		return (""+generatedString+"@reqres.in");
		
	}
	
	public static String id() {
		String generateString = RandomStringUtils.randomNumeric(2);
		return(""+generateString);
	}
	

}
