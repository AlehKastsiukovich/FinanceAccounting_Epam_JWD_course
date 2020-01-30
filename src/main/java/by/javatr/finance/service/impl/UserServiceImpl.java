package by.javatr.finance.service.impl;


import by.javatr.finance.dao.UserDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.dao.factory.DAOFactory;
import by.javatr.finance.entity.User;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.validator.ServiceValidator;


public class UserServiceImpl implements UserService {
	
	@Override
	public boolean signIn(String login, String password) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(login) && !ServiceValidator.iSNotNullObject(password)) {
			throw new ServiceException("Empty login or password!");
		}
		
		UserDAO dao = getUserDAO();
		
		try {
		dao.signIn(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Wrong login or password or such user does not exist!");
		}
		
		return true;
	}

	@Override
	public boolean registration(User user) throws ServiceException {
		if (!ServiceValidator.iSNotNullObject(user)) {
			throw new ServiceException("User object is null!");
		}
		
		UserDAO dao = getUserDAO();
		
		try {
			dao.registration(user);
		} catch (DAOException e) {
			throw new ServiceException();
		}
		
	
		return true;
	}

	private UserDAO getUserDAO() {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		return dao;
	}
}
