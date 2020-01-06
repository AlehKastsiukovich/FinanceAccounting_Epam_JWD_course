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
import by.javatr.finance.dao.UserDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.entity.User;


public class GSONUserDAO implements UserDAO {
	private static GSONUserDAO instance;
	private final String fileName;
	private final File file;
	
	private GSONUserDAO() {
		fileName = "GSONUserStorageFile.txt";
		file = new File(fileName);
	}
	
	public static GSONUserDAO getInstance() {
		if (instance == null) {
			return new GSONUserDAO();
		}
		
		return instance;
	}
	

	@Override
	public Collection<User> getAll() throws DAOException {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new DAOException();
			}
		}
		
		Gson gson = new Gson();
		        
		Collection<User> gsCollection = null;
		
		FileReader reader = null;

		try {
			reader = new FileReader(fileName);
			gsCollection = (Collection<User>) gson.fromJson(reader, new TypeToken<Set<User>>() {
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
	public boolean writeAll(Collection<User> list) throws DAOException {
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
