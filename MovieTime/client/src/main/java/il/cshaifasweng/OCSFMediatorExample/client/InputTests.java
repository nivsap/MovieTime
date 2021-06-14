package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputTests {
	public static Boolean isValidEmail(String email) {
		if(email == null)
			return false;
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(email);  
		return matcher.matches();
	}
	
	public static Boolean isValidPhoneNumber(String phoneNumber) {
		if(phoneNumber == null || phoneNumber.length() != 10)
			return false;
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(phoneNumber);
		if(!matcher.matches())
			return false;
		if(!(phoneNumber.substring(0, 2).equals("05")))
			return false;
		return true;
	}
	
	public static Boolean isValidCVV(String cvv) {
		if(cvv == null || cvv.length() != 3)
			return false;
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(cvv);
		return matcher.matches();
	}
	
	public static Boolean isValidInt(String number) {
		try {
		    Integer.parseInt(number);
		    return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Boolean isValidFloat(String number) {
		try {
		    Float.parseFloat(number);
		    return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Boolean isValidDouble(String number) {
		try {
		    Double.parseDouble(number);
		    return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
