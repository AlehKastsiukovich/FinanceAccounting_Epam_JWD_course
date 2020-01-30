package by.javatr.finance.factory;


import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.impl.ExpenseServiceImpl;
import by.javatr.finance.service.impl.UserServiceImpl;


public class ServiceFactory {
	private final ExpenseService expenseService = new ExpenseServiceImpl();
	private final UserService userService = new UserServiceImpl();
	
	private ServiceFactory() {
		
	}

	public static class ServiceFactoryHolder {
		public static final ServiceFactory instance = new ServiceFactory();
	}

	public static ServiceFactory getInstance() {
		return ServiceFactoryHolder.instance;
	}

	public ExpenseService getExpenseService() {
		return expenseService;
	}
	
	public UserService getUserService() {
		return userService;
	}
}
