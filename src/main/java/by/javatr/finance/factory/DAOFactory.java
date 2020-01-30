package by.javatr.finance.factory;


import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.UserDAO;
import by.javatr.finance.dao.impl.GSONExpenseDAO;
import by.javatr.finance.dao.impl.GSONUserDAO;


public class DAOFactory {
	private final static DAOFactory instance = new DAOFactory();
	
	private final ExpenseDAO expenseGSON = new GSONExpenseDAO();
	private final UserDAO userGSON  = new GSONUserDAO();
	
	private DAOFactory() {
	}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public ExpenseDAO getExpenseDAO() {
		return expenseGSON;
	}
	
	public UserDAO getUserDAO() {
		return userGSON;
	}
}
