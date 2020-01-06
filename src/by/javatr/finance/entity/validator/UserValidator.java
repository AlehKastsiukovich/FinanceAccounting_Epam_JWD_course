package by.javatr.finance.entity.validator;


public class UserValidator {
	public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	public static final String LOGIN_PATTERN = "^[a-zA-Z0-9._-]{3,}$";
	public static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]"
											 + "+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	public static boolean passwordIsValid(String pass) {
		if (pass == null) {
			return false;
		}
		
		if (!pass.matches(PASSWORD_PATTERN)) {
			return false;
		}
		
		return true;
	}
	
	public static boolean loginIsValid(String log) {
		if (log == null) {
			return false;
		}
		
		if (!log.matches(LOGIN_PATTERN)) {
			return false;
		}
		
		return true;
	}
	
	public static boolean emailIsValid(String email) {
		if (email == null) {
			return false;
		}
		
		if (!email.matches(EMAIL_PATTERN)) {
			return false;
		}
		
		return true;
	}
}
