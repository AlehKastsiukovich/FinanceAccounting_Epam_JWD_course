package by.javatr.finance.controller;


import by.javatr.finance.controller.command.Command;


public final class Controller {
	private final CommandProvider provider = new CommandProvider();

	public String executeTask(String request) {
		String commandName = null;
		String response = null;
		Command executionCommand = null;
		char paramSplitter = ' ';

		commandName = request.substring(0, request.indexOf(paramSplitter));
		executionCommand = provider.getCommand(commandName);

		response = executionCommand.execute(request);
		return response;
	}
}
