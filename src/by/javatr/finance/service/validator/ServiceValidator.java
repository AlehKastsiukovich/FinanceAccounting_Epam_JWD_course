package by.javatr.finance.service.validator;


import by.javatr.finance.bean.ExpenseCategory;

public class ServiceValidator {
	
	public static boolean iSNotNullObject(Object object) {
		if (object != null) {
			return true;
		} 
		
		return false;
	}
	
	public static boolean isAmountValid(double amount) {
		if (amount > 0) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isNoteValid(String note) {
		if (note != null) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isCategoryValid(ExpenseCategory category) {
		if (category != null) {
			return true;
		}
		
		return false;
	}
}
