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
public class CommandFileSave extends MyFrameCommand {

    protected void doExecute() throws CommandExecutionException {
        if(frame.getRuta() != null){
				try{
					BufferedImage bi = new BufferedImage(
                            frame.getImage().getWidth(null),
                            frame.getImage().getHeight(null),
                            BufferedImage.TYPE_INT_RGB);
					Graphics2D g2d = bi.createGraphics();
					g2d.drawImage(frame.getImage(),0,0,null);
					g2d.dispose();
					ImageIO.write(bi, "jpg", new File(frame.getRuta()));
				}catch(IOException e){
					System.out.println("Error al escribir el archivo");
				}
			}
    }
}
