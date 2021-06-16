package il.cshaifasweng.OCSFMediatorExample.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputTests {
	public static Boolean isValidEmail(String email)throws Exception {
		if(email == null)
			return false;
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(email);  
		return matcher.matches();
	}
	
	public static Boolean isValidPhoneNumber(String phoneNumber)throws Exception {
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
	
	public static Boolean isValidId(String id)throws Exception {
		if(id == null || id.length() != 9)
			return false;
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}
	
	public static Boolean isValidCreditCard(String creditCard) throws Exception{
		try {
			int[] ints = new int[creditCard.length()];
			int sum = 0;
			
			for (int i = 0; i < creditCard.length(); i++) 
				ints[i] = Integer.parseInt(creditCard.substring(i, i + 1));
			
			for (int i = ints.length - 2; i >= 0; i = i - 2) {
				int j = ints[i];
				j *= 2;
				if (j > 9) 
					j = j % 10 + 1;
				ints[i] = j;
			}
			
			for (int i = 0; i < ints.length; i++) 
				sum += ints[i];
			
			return sum % 10 == 0;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	public static Boolean isValidCVV(String cvv) throws Exception{
		if(cvv == null || cvv.length() != 3)
			return false;
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(cvv);
		return matcher.matches();
	}
	
	public static Boolean isValidDate(String date){
		try {
		    LocalDate.parse(date);
		    return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
	
	public static Boolean isValidDateTime(String dateTime){
		try {
		    LocalDateTime.parse(dateTime);
		    return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
	
	public static Boolean isValidInt(String number){
		try {
		    Integer.parseInt(number);
		    return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Boolean isValidFloat(String number){
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
