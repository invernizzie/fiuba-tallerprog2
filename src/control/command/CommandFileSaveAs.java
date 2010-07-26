package control.command;

import control.command.exceptions.CommandExecutionException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
