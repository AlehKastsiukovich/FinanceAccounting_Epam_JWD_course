package by.javatr.finance.service;


import by.javatr.finance.entity.User;
import by.javatr.finance.exception.ServiceException;


public interface UserService {
	
	public boolean signIn(String login, String password) throws ServiceException;
	
	public boolean registration(User user) throws ServiceException;

}
