package view.components;

import control.command.Command;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public interface CommandComponent {

    void setCommand(Command command);
    Command getCommand();
}
