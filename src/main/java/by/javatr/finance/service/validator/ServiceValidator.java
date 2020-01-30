package by.javatr.finance.service.validator;


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

}
