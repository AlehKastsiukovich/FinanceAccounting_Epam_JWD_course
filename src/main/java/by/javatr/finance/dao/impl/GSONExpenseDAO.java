package by.javatr.finance.dao.impl;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.entity.Expense;
import by.javatr.finance.entity.ExpenseCategory;



public class GSONExpenseDAO implements ExpenseDAO {
	private static final File file = new File("GSONExpenseStorage.txt");
	private Set<Expense> setOfExpenses;
	
	@Override
	public boolean addExpense(Expense expense) throws DAOException {
		setOfExpenses = (Set<Expense>) getAll();
		
		dataCheck(setOfExpenses);
		
		setOfExpenses.add(expense);
		writeAll(setOfExpenses);
		
		return true;
	}
	
	@Override
	public boolean updateExpenseAmount(Expense expense, double newAmount) throws DAOException {
		setOfExpenses = (Set<Expense>) getAll();
		
		dataCheck(setOfExpenses);
		
		for (Expense ex : setOfExpenses) {
			if (ex.equals(expense)) {
				ex.setAmount(newAmount);
				writeAll(setOfExpenses);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean updateExpenseNote(Expense expense, String newNote) throws DAOException {
		setOfExpenses = (Set<Expense>) getAll();
		
		dataCheck(setOfExpenses);
		
		for (Expense ex : setOfExpenses) {
			if (ex.equals(expense)) {
				ex.setNote(newNote);
				writeAll(setOfExpenses);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean updateExpenseCategory(Expense expense, ExpenseCategory newCategory) throws DAOException {
		setOfExpenses = (Set<Expense>) getAll();
		
		dataCheck(setOfExpenses);
		
		for (Expense ex : setOfExpenses) {
			if (ex.equals(expense)) {
				ex.setCategory(newCategory);
				writeAll(setOfExpenses);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteExpense(Expense expense) throws DAOException {
		setOfExpenses = (Set<Expense>) getAll();
		
		dataCheck(setOfExpenses);

		if (setOfExpenses.contains(expense)) {
			setOfExpenses.remove(expense);
			writeAll(setOfExpenses);
			return true;
		}

		
		return false;
	}
	
	@Override
	public Collection<Expense> getAll() throws DAOException {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new DAOException("Can't create file!");
			}
		}
		
		Gson gson = new Gson();
		        
		Collection<Expense> gsCollection = null;
		
		FileReader reader = null;

		try {
			reader = new FileReader(file.getName());
			gsCollection = (Collection<Expense>) gson.fromJson(reader, new TypeToken<Set<Expense>>() {
			}.getType());
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			throw new DAOException("Issues with access to file!");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new DAOException("Can't close stream!");
			}
		}

		return gsCollection;
	}

	@Override
	public boolean writeAll(Collection<Expense> list) throws DAOException {
		if (list == null) {
			throw new DAOException("You can't write empty collection to databas!");
		}
		
		Gson gson = new Gson();
		
		FileWriter writer = null;

		try {
			writer = new FileWriter(file);
			gson.toJson(list, writer);
		} catch (JsonIOException | IOException e) {
			throw new DAOException("Can't create writer object. Issues with writing json to file!");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				throw new DAOException("Can't close writer stream!");
			}
		}

		return true;
	}
	
	private boolean dataCheck(Set<Expense> set) throws DAOException {
		if (set == null) {
			setOfExpenses = new TreeSet<Expense>();
		}
		
		return true;
	}
}


