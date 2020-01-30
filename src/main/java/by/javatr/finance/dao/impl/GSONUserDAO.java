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
import by.javatr.finance.exception.DAOException;
import by.javatr.finance.entity.User;


public class GSONUserDAO implements UserDAO {
	private static final File file = new File("GSONUserStorageFile.txt");
	private Set<User> setOfUsers;
	
	@Override
	public boolean signIn(String login, String password) throws DAOException {
		setOfUsers = (Set<User>) getAll();
		
		for (User user : setOfUsers) {
			if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean registration(User user) throws DAOException {
		setOfUsers = (Set<User>) getAll();

		if (setOfUsers.contains(user)) {
			throw new DAOException("Such user exists!");
		}

		setOfUsers.add(user);
		writeAll(setOfUsers);

		return true;
	}
	
	@Override
	public Collection<User> getAll() throws DAOException {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new DAOException("Can't create new file to users data!");
			}
		}
		
		Gson gson = new Gson();
		        
		Collection<User> gsCollection = null;
		
		FileReader reader = null;

		try {
			reader = new FileReader(file.getName());
			gsCollection = (Collection<User>) gson.fromJson(reader, new TypeToken<Set<User>>() {
			}.getType());
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			throw new DAOException("Issues with getting info from gson format file!");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new DAOException("Can't close reader stream!");
			}
		}

		return gsCollection;
	}

	
	@Override
	public boolean writeAll(Collection<User> list) throws DAOException {
		if (list == null || list.size() == 0) {
			throw new DAOException("Can't write null object or empty collection!");
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
}
