package by.javatr.finance.service.impl;


import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.bean.ExpenseCategory;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.validator.ServiceValidator;


public class ExpenseServiceImpl implements ExpenseService {
	private ExpenseDAO dao;
	private Set<Expense> setOfExpenses;

	public ExpenseServiceImpl(ExpenseDAO dao) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(dao)) {
			throw new ServiceException();
		}

		this.dao = dao;
		setOfExpenses = new TreeSet<Expense>();
	}

	@Override
	public Collection<Expense> findAllExpense() throws ServiceException {
		return daoGetAll();
	}

	@Override
	public boolean addExpense(Expense expense) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(expense)) {
			throw new ServiceException();
		}

		setOfExpenses = daoGetAll();
		setOfExpenses.add(expense);

		try {
			dao.writeAll(setOfExpenses);
		} catch (DAOException e) {
			throw new ServiceException();
		}

		return true;
	}

	@Override
	public boolean updateExpenseAmount(Expense expense, double newAmount) throws ServiceException {
		if (!(ServiceValidator.iSNotNullObject(expense) && ServiceValidator.isAmountValid(newAmount))) {
			throw new ServiceException();
		}

		setOfExpenses = daoGetAll();

		for (Expense ex : setOfExpenses) {

			if (ex.equals(expense)) {
				ex.setAmount(newAmount);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean updateExpenseNote(Expense expense, String newNote) throws ServiceException {
		if (!(ServiceValidator.iSNotNullObject(expense) && ServiceValidator.isNoteValid(newNote))) {
			throw new ServiceException();
		}
	
		setOfExpenses = daoGetAll();

		for (Expense ex : setOfExpenses) {

			if (ex.equals(expense)) {
				ex.setNote(newNote);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateExpenseCategory(Expense expense, ExpenseCategory newCategory) throws ServiceException {
		if (ServiceValidator.iSNotNullObject(expense) && ServiceValidator.iSNotNullObject(newCategory)) {
			throw new ServiceException();
		}
		
		setOfExpenses = daoGetAll();

		for (Expense ex : setOfExpenses) {

			if (ex.equals(expense)) {
				ex.setCategory(newCategory);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean deleteExpense(Expense expense) throws ServiceException {
		if (ServiceValidator.iSNotNullObject(expense)) {
			throw new ServiceException();
		}
		
		setOfExpenses = daoGetAll();

		if (setOfExpenses.contains(expense)) {
			setOfExpenses.remove(expense);

			try {
				dao.writeAll(setOfExpenses);
			} catch (DAOException e) {
				throw new ServiceException();
			}

			return true;
		}

		return false;
	}

	private Set<Expense> daoGetAll() throws ServiceException {
		Set<Expense> set = null;

		try {
			set = (Set<Expense>) dao.getAll();
		} catch (NullPointerException e){
			throw new ServiceException();
		} catch (DAOException e) {
			throw new ServiceException();
		}

		return set;
	}
}
