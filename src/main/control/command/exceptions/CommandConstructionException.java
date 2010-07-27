package main.control.command.exceptions;

import main.control.command.Command;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public class CommandConstructionException extends CommandException {

    public CommandConstructionException(Command command) {
        super(null, command);
    }

    public CommandConstructionException(Throwable cause, Command command) {
        super(cause, command);
    }
}
