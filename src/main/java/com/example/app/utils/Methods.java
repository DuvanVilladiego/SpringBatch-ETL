package com.example.app.utils;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Methods {
	
	public static boolean isValidEmail(String email) {
		  Pattern pattern = Pattern.compile(Constants.emailRegex);
		  Matcher matcher = pattern.matcher(email);
		  return matcher.find();
		}

}
