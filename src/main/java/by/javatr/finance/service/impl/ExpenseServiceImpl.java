package by.javatr.finance.service.impl;


import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.exception.DAOException;
import by.javatr.finance.factory.DAOFactory;
import by.javatr.finance.entity.Expense;
import by.javatr.finance.entity.ExpenseCategory;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.exception.ServiceException;
import by.javatr.finance.validator.ServiceValidator;


public class ExpenseServiceImpl implements ExpenseService {
	
	@Override
	public Collection<Expense> findAllExpense() throws ServiceException {
		return daoGetAll();
	}

	@Override
	public boolean addExpense(Expense expense) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(expense)) {
			throw new ServiceException("Expense object is null!");
		}

		ExpenseDAO dao = getExpenseDAO();

		try {
			dao.addExpense(expense);
		} catch (DAOException e) {
			throw new ServiceException("Can't write Expense to database!");
		}

		return true;
	}

	@Override
	public boolean updateExpenseAmount(Expense expense, double newAmount) throws ServiceException {
		if (!(ServiceValidator.iSNotNullObject(expense) && ServiceValidator.isAmountValid(newAmount))) {
			throw new ServiceException("Wrong method parameters. Expense is null or amount is not valid!");
		}

		ExpenseDAO dao = getExpenseDAO();

		try {
			dao.updateExpenseAmount(expense, newAmount);
		} catch (DAOException e) {
			throw new ServiceException("Can't uptdate this expense!");
		}

		return true;
	}

	@Override
	public boolean updateExpenseNote(Expense expense, String newNote) throws ServiceException {
		if (!(ServiceValidator.iSNotNullObject(expense) && ServiceValidator.iSNotNullObject(newNote))) {
			throw new ServiceException("Wrong method parameters. Expense is null or note is null!");
		}

		ExpenseDAO dao = getExpenseDAO();

		try {
			dao.updateExpenseNote(expense, newNote);
		} catch (DAOException e) {
			throw new ServiceException("Can't uptdate this expense!");
		}

		return true;
	}

	@Override
	public boolean updateExpenseCategory(Expense expense, ExpenseCategory newCategory) throws ServiceException {
		if (!(ServiceValidator.iSNotNullObject(expense) && ServiceValidator.iSNotNullObject(newCategory))) {
			throw new ServiceException("Wrong method parameters. Expense is null or category is null!");
		}

		ExpenseDAO dao = getExpenseDAO();

		try {
			dao.updateExpenseCategory(expense, newCategory);
		} catch (DAOException e) {
			throw new ServiceException("Can't uptdate this expense!");
		}

		return true;
	}

	@Override
	public boolean deleteExpense(Expense expense) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(expense)) {
			throw new ServiceException("Expense object is null!");
		}

		ExpenseDAO dao = getExpenseDAO();
		
		try {
			dao.deleteExpense(expense);
		} catch (DAOException e) {
			throw new ServiceException("Can't delete this expense!");
		}
		
		return true;
	}

	@Override
	public Set<Expense> filterByDate(Set<Expense> expenses, Date after, Date before) throws ServiceException {
		Set<Expense> setOfExpense = daoGetAll();

		for (Expense exp : setOfExpense) {
			if ((exp.getExpenseDate().after(after)) & (exp.getExpenseDate().before(before))) {
				setOfExpense.add(exp);
			}
		}

		return setOfExpense;
	}

	private Set<Expense> daoGetAll() throws ServiceException {
		Set<Expense> setOfExpenses = null;

		ExpenseDAO dao = getExpenseDAO();

		try {
			setOfExpenses = (Set<Expense>) dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException("Can't read collection from database!");
		}

		if (!ServiceValidator.iSNotNullObject(setOfExpenses)) {
			setOfExpenses = new TreeSet<Expense>();
		}

		return setOfExpenses;
	}

	private ExpenseDAO getExpenseDAO() {
		DAOFactory factory = DAOFactory.getInstance();
		ExpenseDAO dao = factory.getExpenseDAO();

		return dao;
	}
}
