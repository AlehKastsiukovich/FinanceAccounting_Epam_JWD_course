package by.javatr.finance.service;


import by.javatr.finance.entity.Expense;
import by.javatr.finance.entity.ExpenseCategory;
import by.javatr.finance.service.exeption.ServiceException;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


public interface ExpenseService {
	
	Collection<Expense> findAllExpense() throws ServiceException;
	
	boolean addExpense(Expense expense) throws ServiceException;
	
	boolean updateExpenseAmount(Expense expense, double newAmount) throws ServiceException;
	
	boolean updateExpenseNote(Expense expense, String newNote) throws ServiceException;
	
	boolean updateExpenseCategory(Expense expense, ExpenseCategory newCategory) throws ServiceException;
	
	boolean deleteExpense(Expense expense) throws ServiceException; 
	
	Set<Expense> filterByDate(Set<Expense> expenses, Date after, Date before) throws ServiceException;
	
	
}
