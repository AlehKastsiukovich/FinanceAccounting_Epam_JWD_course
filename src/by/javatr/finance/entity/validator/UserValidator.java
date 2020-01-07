package by.javatr.finance.entity.validator;


import java.util.regex.Pattern;


public class UserValidator {
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
																	+ "(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
	private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]"
			 														+ "+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
	
	
	public static boolean passwordIsValid(String pass) {
		if (pass == null) {
			return false;
		}
		
		if (!PASSWORD_PATTERN.matcher(pass).matches()) {
			return false;
		}
		
		return true;
	}
	
	public static boolean loginIsValid(String log) {
		if (log == null) {
			return false;
		}
		
		if (!LOGIN_PATTERN.matcher(log).matches()) {
			return false;
		}
		
		return true;
	}
	
	public static boolean emailIsValid(String email) {
		if (email == null) {
			return false;
		}
		
		if (!EMAIL_PATTERN.matcher(email).matches()) {
			return false;
		}
		
		return true;
	}
}
