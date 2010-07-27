package main.control.command.exceptions;

import main.control.command.Command;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public class CommandExecutionException extends CommandException {

    public CommandExecutionException(Command command) {
        super(null, command);
    }

    public CommandExecutionException(Throwable cause, Command command) {
        super(cause, command);
    }
}
