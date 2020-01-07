package by.javatr.finance.service.impl;


import java.util.Set;
import java.util.TreeSet;
import javax.sql.rowset.serial.SerialException;
import by.javatr.finance.dao.UserDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.entity.User;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.validator.ServiceValidator;


public class UserServiceImpl implements UserService {
	private UserDAO dao;
	private Set<User> setOfUsers;

	public UserServiceImpl(UserDAO dao) throws SerialException {
		if (!ServiceValidator.iSNotNullObject(dao)) {
			throw new SerialException("Dao object is null");
		}

		this.dao = dao;
		setOfUsers = new TreeSet<User>();
	}

	@Override
	public boolean signIn(String login, String password) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(login) && !ServiceValidator.iSNotNullObject(password)) {
			throw new ServiceException("Empty login or password!");
		}
		
		setOfUsers = daoGetAll();
		
		for (User user : setOfUsers) {
			if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean registration(User user) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(user)) {
			throw new ServiceException("User object is null!");
		}
		
		setOfUsers = daoGetAll();
		
		if (setOfUsers.contains(user)) {
			throw new ServiceException("Such user already exist!");
		}
		
		setOfUsers.add(user);
		
		try {
			dao.writeAll(setOfUsers);
		} catch (DAOException e) {
			throw new ServiceException("Can't write collection to database!");
		}
		
		return true;
	}

	private Set<User> daoGetAll() throws ServiceException {
		Set<User> setOfUsers = null;

		try {
			this.setOfUsers = (Set<User>) dao.getAll();
		} catch (NullPointerException e) {
			throw new ServiceException("Dao object is null!");
		} catch (DAOException e) {
			throw new ServiceException("Can't read data from database!");
		}

		if (!ServiceValidator.iSNotNullObject(setOfUsers)) {
			setOfUsers = new TreeSet<User>();
		}

		return setOfUsers;

	}
}
