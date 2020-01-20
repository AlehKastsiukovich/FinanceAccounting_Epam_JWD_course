package by.javatr.finance.dao;


import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.entity.User;


public interface UserDAO extends ModelDAO<User>{
	
	boolean signIn(String login, String password) throws DAOException;
	
	boolean registration(User user) throws DAOException;
}
