package control;

import java.awt.Checkbox;
import java.awt.Dialog;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import javax.swing.JOptionPane;

import model.filters.Filter;

import view.components.MyFrame;
import control.command.CommandFactory;
import control.command.MyFrameCommand;
import control.command.exceptions.CommandConstructionException;
import control.command.exceptions.CommandException;
import control.command.exceptions.CommandExecutionException;

public class FilterSelectorHandler implements ActionListener {
	
	private List listaDisponibles;
	private List listaSeleccionados;
	private Checkbox chkDefaults;
	private Checkbox chkSaveFilter;
	private Dialog d;
	private MyFrame frame;
	
	public FilterSelectorHandler(List listaDisponibles, List listaSeleccionados, Checkbox chkDefaults, Checkbox chkSaveFilter, Dialog d, MyFrame frame){
		this.listaDisponibles = listaDisponibles;
		this.listaSeleccionados = listaSeleccionados;
		this.d = d;
		this.frame = frame;
		this.chkDefaults = chkDefaults;
		this.chkSaveFilter = chkSaveFilter;
	}

	
	public void actionPerformed(ActionEvent ae) {
		String actionCommand = (String)ae.getActionCommand();
		MyFrameCommand command = null;
		try {
			command = CommandFactory.buildCommand(actionCommand, frame, this);
			command.execute();
		} catch (CommandConstructionException cce) {
			closeAll(cce);
		} catch (CommandExecutionException cee) {
			closeAll(cee);
		}
	}


	public List getListaDisponibles() {
		return listaDisponibles;
	}

	public List getListaSeleccionados() {
		return listaSeleccionados;
	}

	public Checkbox getChkDefaults() {
		return chkDefaults;
	}
	
	public Checkbox getChkSaveFilter() {
		return chkSaveFilter;
	}

	public Dialog getDialog() {
		return d;
	}
	
	
	public void closeAll(CommandException e) {
		String command = (e.getCommand() == null) ? "" : e.getCommand().toString();
		String cause = e.getCause() == null ? "" : ("Causa: " + e.getCause().toString());
		JOptionPane.showMessageDialog(
		        d, command + "\n" + cause,
		        "Error al construir Command",
		        JOptionPane.ERROR_MESSAGE);
		d.dispose();
		frame.dispose();
		System.exit(0);
	}



}
