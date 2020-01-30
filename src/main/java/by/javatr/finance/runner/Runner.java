package by.javatr.finance.runner;


import java.util.Date;
import by.javatr.finance.entity.Expense;
import by.javatr.finance.entity.ExpenseCategory;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;


public class Runner {
	
	public static void main(String[] args) {
		
		Expense expense = new Expense.ExpenseBuilder()
									 .buildAmount(20.00).buildNote("hello")
									 .buildCategory(ExpenseCategory.HEALTH)
									 .buildDate(new Date())
									 .build();
		
		Expense expense2 = new Expense.ExpenseBuilder()
									 .buildAmount(25).buildNote(null)
									 .buildCategory(null)
									 .buildDate(new Date())
									 .build();
		
		Expense expense3 = new Expense.ExpenseBuilder()
									 .buildAmount(9).buildNote("!")
									 .buildCategory(ExpenseCategory.RESTAURANT)
									 .buildDate(new Date())
									 .build();
		
		Expense expense4 = new Expense.ExpenseBuilder()
									 .buildAmount(1000).buildNote("4")
									 .buildCategory(ExpenseCategory.SHOPPING)
									 .buildDate(new Date())
									 .build();
		
		Expense expense5 = new Expense.ExpenseBuilder()
									 .buildAmount(322).buildNote("5")
									 .buildCategory(ExpenseCategory.TRANSPORT)
									 .buildDate(new Date())
									 .build();
		
		Expense expense6 = new Expense.ExpenseBuilder()
									 .buildAmount(400).buildNote("22")
									 .buildCategory(ExpenseCategory.TRANSPORT)
									 .buildDate(new Date())
									 .build();
		
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ExpenseService service = factory.getExpenseService();
		
		/*
		 * try {
			service.addExpense(expense);
			service.addExpense(expense2);
			service.addExpense(expense3);
			service.addExpense(expense4);
			service.addExpense(expense5);
			service.addExpense(expense6);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		 */
		
		try {
			service.deleteExpense(expense);
		} catch (ServiceException e) {
		
		}

		try {
			service.updateExpenseAmount(expense2, 99999);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
		
		}
	}
}
