package by.javatr.finance.controller;


import java.util.HashMap;
import java.util.Map;
import by.javatr.finance.command.Command;
import by.javatr.finance.command.impl.AddExpense;
import by.javatr.finance.command.impl.DeleteExpense;
import by.javatr.finance.command.impl.Registration;
import by.javatr.finance.command.impl.SignIn;
import by.javatr.finance.command.impl.WrongRequest;


final class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<CommandName, Command>();
	
	public CommandProvider() {
		repository.put(CommandName.SIGN_IN, new SignIn());
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.ADD_EXPENSE, new AddExpense());
		repository.put(CommandName.DELETE_EXPENSE, new DeleteExpense());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());	
	}
	
	Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		
		return command;
	}
	
	
 
}
