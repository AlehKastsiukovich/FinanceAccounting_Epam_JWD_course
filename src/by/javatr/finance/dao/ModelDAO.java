package by.javatr.finance.dao;


import java.util.Collection;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.dao.exception.DAOException;

public interface ModelDAO<T> {
	//Optional<T> get(int id);
	
    Collection<T> getAll() throws DAOException;
    
    boolean writeAll(Collection<Expense> list) throws DAOException;
    
    //boolean add(T t);
    //boolean update(T t);
    
    //boolean delete(T t);
}
