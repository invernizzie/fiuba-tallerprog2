package main.control.command;

import main.view.components.MyFrame;
import main.control.command.exceptions.CommandExecutionException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public abstract class MyFrameCommand implements Command {

    MyFrame frame;

    public final void execute() throws CommandExecutionException {
        testExecute();
        doExecute();
    }

    protected abstract void doExecute() throws CommandExecutionException;

    protected void testExecute() throws CommandExecutionException {
        if (frame == null)
            throw new CommandExecutionException(this);
    }
    
    public MyFrame getFrame() {
        return frame;
    }

    public void setFrame(MyFrame frame) {
        this.frame = frame;
    }
}
