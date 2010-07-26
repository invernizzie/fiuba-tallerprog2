package control;

import java.awt.Dialog;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.components.MyFrame;

public class MyDialogHandler implements ActionListener {
	
	private Dialog d;
	private MyFrame myframe;
	
	public MyDialogHandler(Dialog d, MyFrame f){
		this.d = d;
		this.myframe = f;
	}

	public void actionPerformed(ActionEvent ae) {
		String arg = (String)ae.getActionCommand();
		if(arg.equals("Aceptar")){
			int ancho = 0;
			int alto = 0;
			try{
				TextField tf1 = (TextField)d.getComponent(1);
				TextField tf2 = (TextField)d.getComponent(3);
				ancho = Integer.parseInt(tf1.getText());
				alto = Integer.parseInt(tf2.getText());
			}catch(NumberFormatException e){
				ancho = 0;
				alto = 0;
			}
			myframe.setAncho(ancho);
			myframe.setAlto(alto);
			myframe.repaint();
			d.dispose();
		}
		else if(arg.equals("Cancelar")){
			d.dispose();
		}
	}

}
