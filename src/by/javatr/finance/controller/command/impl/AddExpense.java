package by.javatr.finance.controller.command.impl;


import java.util.Date;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.entity.Expense;
import by.javatr.finance.entity.Expense.ExpenseBuilder;
import by.javatr.finance.entity.ExpenseCategory;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;


public class AddExpense implements Command {

	@Override
	public String execute(String request) {
		String[] requestArr = request.split(" ");
		
		Expense expense = null;
		String response = null;
		
		expense = new ExpenseBuilder().buildAmount(Double.parseDouble(requestArr[1]))
									  .buildCategory(ExpenseCategory.valueOf(requestArr[2]))
									  .buildDate(new Date())
									  .buildNote(requestArr[3])
									  .build();
									  	
				
		ServiceFactory factory = ServiceFactory.getInstance();
		ExpenseService service = factory.getExpenseService();
		
		try {
			service.addExpense(expense);
			response = "Expense has been added!";
		} catch (ServiceException e) {
			response = "Adding error";
		}
		
		return response;
	}
}
