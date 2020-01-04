package by.javatr.finance.runner;

import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.impl.GSONExpenseDAO;
import by.javatr.finance.entity.*;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.impl.ExpenseServiceImpl;


public class Runner {

	public static void main(String[] args) {

		ExpenseDAO dao = new GSONExpenseDAO();

		/*
		 * dao.writeAll(expense); ExpenseService service = new ExpenseServiceImpl(dao);
		 * 
		 * List<Expense> newList = (List<Expense>) service.findAllExpense();
		 * 
		 * for (Expense ex : newList) { System.out.println(ex)
		 */
		
		
		
		System.out.println("Continue program");
	}
}
