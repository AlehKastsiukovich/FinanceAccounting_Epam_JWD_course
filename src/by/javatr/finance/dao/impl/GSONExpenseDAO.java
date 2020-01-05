package by.javatr.finance.dao.impl;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.entity.Expense;


public class GSONExpenseDAO implements ExpenseDAO {
	private static GSONExpenseDAO instance;
	private final String fileName;
	private final File file;
	
	public GSONExpenseDAO() {
		fileName = "GSONStorage.txt";
		file = new File(fileName);
	}

	@Override
	public Collection<Expense> getAll() throws DAOException {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new DAOException();
			}
		}
		
		Gson gson = new Gson();
		
		Collection<Expense> gsCollection = null;
		
		FileReader reader = null;

		try {
			reader = new FileReader(fileName);
			gsCollection = (Collection<Expense>) gson.fromJson(reader, new TypeToken<Set<Expense>>() {
			}.getType());
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			throw new DAOException();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new DAOException();
			}
		}

		return gsCollection;
	}

	@Override
	public boolean writeAll(Collection<Expense> list) throws DAOException {
		if (list == null || list.size() == 0) {
			throw new DAOException();
		}
		
		Gson gson = new Gson();
		
		FileWriter writer = null;

		try {
			writer = new FileWriter(file);
			gson.toJson(list, writer);
		} catch (JsonIOException | IOException e) {
			throw new DAOException();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				throw new DAOException();
			}
		}

		return true;
	}
}

