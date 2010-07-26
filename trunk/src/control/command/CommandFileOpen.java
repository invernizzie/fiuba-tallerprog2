package control.command;

import java.awt.FileDialog;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.command.exceptions.CommandExecutionException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandFileOpen extends MyFrameCommand {


    protected void doExecute() throws CommandExecutionException {
        FileDialog fd = new FileDialog(frame,"Abrir",FileDialog.LOAD);
			fd.setFile("*.jpg");
			fd.setLocation(new Point(350,120));
			fd.setVisible(true);
			if (fd.getFile()!=null && fd.getDirectory()!=null){
				BufferedImage img = null;
				try {
				    img = ImageIO.read(new File(fd.getDirectory()+fd.getFile()));
				    frame.setRuta(fd.getDirectory() + fd.getFile());
				    frame.setImage(img);
                    frame.setStrokes(null);
                    frame.setProfile(null);
				    frame.setImageOrig(img);
				    frame.enableProcessing();
				    frame.repaint();
				} catch (IOException e) {
					System.out.println("Error al abrir el archivo");
				}
            }
    }

}
