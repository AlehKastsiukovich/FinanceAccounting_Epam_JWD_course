package by.javatr.finance.runner;


import java.util.Date;
import java.util.Set;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.impl.GSONExpenseDAO;
import by.javatr.finance.entity.*;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.impl.ExpenseServiceImpl;


public class Runner {

	public static void main(String[] args) {

		ExpenseDAO dao = new GSONExpenseDAO();

		
		Expense expense = new Expense.ExpenseBuilder()
									 .buildAmount(100.00).buildNote("hello")
									 .buildCategory(ExpenseCategory.HEALTH)
									 .buildDate(new Date())
									 .build();
		
		Expense expense2 = new Expense.ExpenseBuilder()
									 .buildAmount(25).buildNote("world")
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
									 .buildAmount(400).buildNote("6")
									 .buildCategory(ExpenseCategory.TRANSPORT)
									 .buildDate(new Date())
									 .build();
		
	
		ExpenseService service = null;
		
		try {
			service = new ExpenseServiceImpl(dao);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			service.addExpense(expense);
			service.addExpense(expense2);
			service.addExpense(expense3);
			service.addExpense(expense4);
			service.addExpense(expense5);
			service.addExpense(expense6);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<Expense> set = null;
		
		try {
			set = (Set<Expense>) service.findAllExpense();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Expense exp : set) {
			System.out.println(exp.toString());
		}
		
		System.out.println("_________________________");
		
	
		try {
			service.deleteExpense(expense2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			set = (Set<Expense>) service.findAllExpense();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Expense exp : set) {
			System.out.println(exp.toString());
		}
	}
}
