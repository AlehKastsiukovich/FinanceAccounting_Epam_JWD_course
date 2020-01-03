package by.javatr.finance.service;


import by.javatr.finance.bean.Expense;
import by.javatr.finance.bean.ExpenseCategory;
import by.javatr.finance.service.exeption.ServiceException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface ExpenseService {
	
	Collection<Expense> findAllExpense() throws ServiceException;
	
	boolean addExpense(Expense expense) throws ServiceException;
	
	boolean updateExpenseAmount(Expense expense, double newAmount) throws ServiceException;
	
	boolean updateExpenseNote(Expense expense, String newNote) throws ServiceException;
	
	boolean updateExpenseCategory(Expense expense, ExpenseCategory newCategory) throws ServiceException;
	
	boolean deleteExpense(Expense expense) throws ServiceException; 
	
}
