package by.javatr.finance.controller.command.impl;


import by.javatr.finance.controller.command.Command;
import by.javatr.finance.entity.Expense;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;


public class DeleteExpense implements Command {

	@Override
	public String execute(String request) {
		Expense expense = null;
		String response = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ExpenseService service = factory.getExpenseService();
		
		try {
			service.deleteExpense(expense);
			response = "Expense has been deleted!";
		} catch (ServiceException e) {
			response = "Delete error! ";
		}
		
		return response;
	}
}
