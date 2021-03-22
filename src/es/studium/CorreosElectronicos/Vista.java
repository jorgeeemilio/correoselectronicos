package es.studium.CorreosElectronicos;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;

public class Vista
{
	Frame ventana = new Frame("Correos Electrónicos");
	TextField txtRuta = new TextField(20);
	Button btnAnalizar = new Button("Analizar");
	TextArea txaResultados = new TextArea(20,25);
	
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("Proceso finalizado");
	
	public Vista()
	{
		ventana.setLayout(new FlowLayout());
		
		ventana.add(txtRuta);
		ventana.add(btnAnalizar);
		ventana.add(txaResultados);
		
		ventana.setSize(220,440);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		txtRuta.requestFocus();
		ventana.setVisible(true);
		
		dlgMensaje.setLayout(new FlowLayout());
		dlgMensaje.setSize(300, 100);
		dlgMensaje.setLocationRelativeTo(null);
		dlgMensaje.add(lblMensaje);
	}
}