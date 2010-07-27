package main.view.components;

import main.control.command.Command;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
// TODO Cambiar su uso por CommandMenuItem, no se usa usan mas como checkbox
public class CommandCheckboxMenuItem extends CheckboxMenuItem implements CommandComponent {

    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
