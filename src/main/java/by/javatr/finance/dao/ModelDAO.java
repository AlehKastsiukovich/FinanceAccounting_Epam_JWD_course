package by.javatr.finance.dao;


import java.util.Collection;
import by.javatr.finance.exception.DAOException;


public interface ModelDAO<T> {

    Collection<T> getAll() throws DAOException;
    
    boolean writeAll(Collection<T> list) throws DAOException;
}
