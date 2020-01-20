package by.javatr.finance.service.exeption;


public class ServiceException extends Exception{
	
	public ServiceException() {
		
	}
	
	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
}
