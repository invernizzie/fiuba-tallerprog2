package control.command;

import control.command.exceptions.CommandExecutionException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandReset extends MyFrameCommand {

    protected void doExecute() throws CommandExecutionException {
        frame.setImage(frame.getImageOrig());
        frame.setStrokes(null);
        frame.setProfile(null);
        frame.repaint();
    }
}
