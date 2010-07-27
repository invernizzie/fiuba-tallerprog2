package main.control.command;

import main.control.command.exceptions.CommandExecutionException;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandFileSaveAs extends CommandFileSave {

    protected void doExecute() throws CommandExecutionException {
        FileDialog fd = new FileDialog(frame, "Guardar Como", FileDialog.SAVE);
        fd.setFile("*.jpg");
        fd.setLocation(new Point(350,120));
        fd.setVisible(true);

        if (fd.getFile() == null && fd.getDirectory() == null)
            return;

        frame.setRuta(fd.getDirectory() + fd.getFile());
        super.execute();
    }
}
