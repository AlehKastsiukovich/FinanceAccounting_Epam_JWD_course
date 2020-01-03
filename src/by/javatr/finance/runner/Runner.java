package by.javatr.finance.runner;

import by.javatr.finance.bean.*;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.impl.GSONExpenseDAO;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.impl.ExpenseServiceImpl;


public class Runner {

	public static void main(String[] args) {
		Expense expense1 = new Expense(1, 2.0, "hello", ExpenseCategory.HEALTH);
		Expense expense2 = new Expense(2, 2.0, "hello", ExpenseCategory.HEALTH);
		Expense expense3 = new Expense(3, 5.0, "yo", ExpenseCategory.GROCERIES);

		ExpenseDAO dao = new GSONExpenseDAO();

		/*
		 * dao.writeAll(expense); ExpenseService service = new ExpenseServiceImpl(dao);
		 * 
		 * List<Expense> newList = (List<Expense>) service.findAllExpense();
		 * 
		 * for (Expense ex : newList) { System.out.println(ex)
		 */
		
		ExpenseService service = null;
		try {
			service = new ExpenseServiceImpl(dao);
		} catch (ServiceException e1) {
			
		}
			
		try {
			service.addExpense(expense1);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service.addExpense(expense2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			service.addExpense(expense3);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			service.updateExpenseNote(expense1, null);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Continue program");
	}
}
