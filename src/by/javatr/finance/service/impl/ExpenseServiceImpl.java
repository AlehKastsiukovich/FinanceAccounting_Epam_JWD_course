package by.javatr.finance.service.impl;


import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.entity.Expense;
import by.javatr.finance.entity.ExpenseCategory;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.validator.ServiceValidator;


public class ExpenseServiceImpl implements ExpenseService {
	private ExpenseDAO dao;
	private Set<Expense> setOfExpenses;
	
	
	public ExpenseServiceImpl(ExpenseDAO dao) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(dao)) {
			throw new ServiceException("Dao object is null!");
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
			throw new ServiceException("Expense object is null!");
		}

		setOfExpenses = daoGetAll();
		setOfExpenses.add(expense);

		try {
			dao.writeAll(setOfExpenses);
		} catch (DAOException e) {
			throw new ServiceException("Can't write collection to database!");
		}

		return true;
	}

	@Override
	public boolean updateExpenseAmount(Expense expense, double newAmount) throws ServiceException {
		if (!(ServiceValidator.iSNotNullObject(expense) && ServiceValidator.isAmountValid(newAmount))) {
			throw new ServiceException("Wrong method parameters. Expense is null or amount is not valid!");
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
		if (!(ServiceValidator.iSNotNullObject(expense) && ServiceValidator.iSNotNullObject(newNote))) {
			throw new ServiceException("Wrong method parameters. Expense is null or note is null!");
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
		if (!(ServiceValidator.iSNotNullObject(expense) && ServiceValidator.iSNotNullObject(newCategory))) {
			throw new ServiceException("Wrong method parameters. Expense is null or category is not null!");
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
		if (!ServiceValidator.iSNotNullObject(expense)) {
			throw new ServiceException("Expense object is null!");
		}
		
		setOfExpenses = daoGetAll();

		if (setOfExpenses.contains(expense)) {
			setOfExpenses.remove(expense);

			try {
				dao.writeAll(setOfExpenses);
			} catch (DAOException e) {
				throw new ServiceException("Can't write collection to database!");
			}

			return true;
		}

		return false;
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

		try {
			setOfExpenses = (Set<Expense>) dao.getAll();
		} catch (NullPointerException e){
			throw new ServiceException("Dao object is null!");
		} catch (DAOException e) {
			throw new ServiceException("Can't read collection from database!");
		}
		
		if (!ServiceValidator.iSNotNullObject(setOfExpenses)) {
			setOfExpenses = new TreeSet<Expense>();
		}

		return setOfExpenses;
	}
}
