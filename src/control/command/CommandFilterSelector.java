package control.command;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import control.FilterEnum;
import control.FilterSelectorHandler;
import control.MasksEnum;
import control.command.exceptions.CommandExecutionException;

public class CommandFilterSelector extends MyFrameCommand {

	private FilterSelectorHandler handler;
	
	
	@Override
	protected void doExecute() throws CommandExecutionException {
        final Dialog d = new Dialog(frame, "Secuencia de filtros", true);
        d.setResizable(false);
        d.setSize(new Dimension(400,280));
        d.setLocation(new Point(500,300));
        d.setLayout(new FlowLayout());


        List listaDisponibles;
        List listaSeleccionados;
        if(handler==null){
        	listaSeleccionados = new List(10, false);
        	listaDisponibles = new List(10, false);
        	this.completarListaDisponibles(listaDisponibles);
        }
        else{
        	listaSeleccionados = handler.getListaSeleccionados();
        	listaDisponibles = handler.getListaDisponibles();
        }


        Checkbox chkDefault = new Checkbox("Usar parametros de filtro por defecto?");
        Checkbox chkSaveFilter = new Checkbox("Guardar seleccion?");
        chkDefault.setState(true);
        chkSaveFilter.setState(false);
        handler = new FilterSelectorHandler(listaDisponibles, listaSeleccionados,chkDefault,chkSaveFilter, d, frame);
        Button btnIzquierda = new Button("<<");
        Button btnDerecha = new Button(">>");
        Button btnAplicar = new Button("Aplicar"); 
        Button btnCancelar = new Button("Cancelar");
       
        btnIzquierda.setActionCommand(CommandFactory.REMOVE_FILTER);
        btnDerecha.setActionCommand(CommandFactory.ADD_FILTER);
        btnAplicar.setActionCommand(CommandFactory.APPLY_FILTER_LIST);
        btnIzquierda.addActionListener(handler);
        btnDerecha.addActionListener(handler);
        btnAplicar.addActionListener(handler);
        btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.dispose();
			}
		});
        
        d.add(chkDefault);
        d.add(chkSaveFilter);
        d.add(listaDisponibles);
        d.add(btnIzquierda);
        d.add(btnDerecha);
        d.add(listaSeleccionados);
        d.add(btnAplicar);
        d.add(btnCancelar);
        
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

	public FilterSelectorHandler getHandler() {
		return handler;
	}


	private void completarListaDisponibles(List listaDisponibles) {
		MasksEnum[] mascaras = MasksEnum.values();
		for (int i = 0; i < mascaras.length; i++) {
			MasksEnum mascara = mascaras[i];
			mascara.getNombre();
			listaDisponibles.add(mascara.getNombre(), i);
		}
		
		FilterEnum[] filtros = FilterEnum.values();
		for (int i = 0; i < filtros.length; i++) {
			FilterEnum filtro = filtros[i];
			filtro.getNombre();
			listaDisponibles.add(filtro.getNombre(), i);
		}
	}


}
