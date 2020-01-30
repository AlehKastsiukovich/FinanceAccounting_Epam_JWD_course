package by.javatr.finance.controller.command.impl;


import by.javatr.finance.controller.command.Command;
import by.javatr.finance.entity.User;
import by.javatr.finance.entity.User.UserBuilder;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exeption.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;


public class Registration implements Command{

	@Override
	public String execute(String request) {
		String[] requestArr = request.split(" ");
		
		String response = null;
		User user = null;
		
		user = new UserBuilder().buildLogin(requestArr[1])
								.buildPassword(requestArr[2])
								.buildEmail(requestArr[3])
								.build();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();
		
		try {
			service.registration(user);
			response = "Registration completed!";
		} catch (ServiceException e) {
			response = "Registration error!";
		}
		
		return response;
	}

}
