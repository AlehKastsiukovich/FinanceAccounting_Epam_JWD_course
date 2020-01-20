package by.javatr.finance.dao;


import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.entity.Expense;
import by.javatr.finance.entity.ExpenseCategory;


public interface ExpenseDAO extends ModelDAO<Expense> {
	
	boolean addExpense(Expense expense) throws DAOException;
	
	boolean updateExpenseAmount(Expense expense, double newAmount) throws DAOException;
	
	boolean updateExpenseNote(Expense expense, String newNote) throws DAOException;
	
	boolean updateExpenseCategory(Expense expense, ExpenseCategory newCategory) throws DAOException;
	
	boolean deleteExpense(Expense expense) throws DAOException;

}
