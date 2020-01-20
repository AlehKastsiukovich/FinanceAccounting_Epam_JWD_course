package by.javatr.finance.controller.command.impl;


import by.javatr.finance.controller.command.Command;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;


public class SignIn implements Command {

	@Override
	public String execute(String request) {
		String response = null;
		String login = null;
		String password = null;
		
		String[] reqArray = request.split(" ");
		login = reqArray[1];
		password = reqArray[2];
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		
		try {
			service.signIn(login, password);
			response = "Login complete";
		} catch (ServiceException e) {
			response = "Login error!";
		}
		
		return response;
	}
}
