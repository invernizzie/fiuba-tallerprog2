package control.command;

import control.command.exceptions.CommandExecutionException;


public class CommandDFT extends MyFrameCommand {

    protected void doExecute() throws CommandExecutionException{
  
    }

    protected void testExecute() throws CommandExecutionException {
        super.testExecute();
        if (getFrame().getImage() == null)
            throw new CommandExecutionException(this);
    }
}
