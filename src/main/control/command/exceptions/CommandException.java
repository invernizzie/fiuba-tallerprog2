package main.control.command.exceptions;

import main.control.command.Command;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public abstract class CommandException extends NestedException {

    Command command;

    CommandException(Throwable cause, Command command) {
        super(cause);
        this.command = command;
    }

	public Command getCommand() {
        return command;
    }
}
