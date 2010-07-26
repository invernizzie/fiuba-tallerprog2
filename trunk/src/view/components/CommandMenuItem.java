package view.components;

import control.command.Command;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandMenuItem extends MenuItem implements CommandComponent {

    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
