package main.control.command;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import main.control.MyDialogHandler;
import main.control.command.exceptions.CommandExecutionException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandResize extends MyFrameCommand {

    protected void doExecute() throws CommandExecutionException {
        Dialog d = new Dialog(frame, "Ajustar Tamaño", true);
        d.setSize(new Dimension(250,100));
        d.setLocation(new Point(500,300));
        d.setLayout(new FlowLayout());
        TextField ancho, alto;
        ancho = new TextField(3);
        alto = new TextField(3);
        d.add(new Label("Ancho: ",Label.RIGHT));
        d.add(ancho);
        d.add(new Label("Alto: ",Label.RIGHT));
        d.add(alto);
        Button accept,cancel;
        d.add(accept = new Button("Aceptar"));
        d.add(cancel = new Button("Cancelar"));
        MyDialogHandler dh = new MyDialogHandler(d, frame);
        accept.addActionListener(dh);
        cancel.addActionListener(dh);
        d.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e){}
			public void windowDeactivated(WindowEvent e){}
			public void windowIconified(WindowEvent e){}
			public void windowDeiconified(WindowEvent e){}
			public void windowOpened(WindowEvent e){}
			public void windowClosed(WindowEvent e){}
			public void windowClosing(WindowEvent e){
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
			}
		});	
        d.setVisible(true);
    }
}
