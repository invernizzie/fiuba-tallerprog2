package control.command;

import control.command.exceptions.CommandExecutionException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandExit extends MyFrameCommand {

    public void doExecute() throws CommandExecutionException {
        frame.dispose();
        System.exit(0);
    }
}
