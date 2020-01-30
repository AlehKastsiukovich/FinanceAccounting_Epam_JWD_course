package by.javatr.finance.entity.validator;


public class ExpenseValidator {
	
	public static boolean isNotNullObject(Object object) {
		if (object != null) {
			return true;
		}
		
		return false;
	}
	
	 public static boolean isNumberValid(double number) {
		 if (number > 0) {
			 return true;
		 }
		 
		 return false;
	 }
}
