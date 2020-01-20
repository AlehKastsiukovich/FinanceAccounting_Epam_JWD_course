package by.javatr.finance.dao.exception;


public class DAOException extends Exception {
	
	public DAOException() {
		
	}
	
	public DAOException(String msg) {
		super(msg);
	}
	
	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException(String message, Exception e) {
		super(message, e);
	}
}
