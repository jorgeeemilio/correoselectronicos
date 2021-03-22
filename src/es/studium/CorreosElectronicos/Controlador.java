package es.studium.CorreosElectronicos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener
{
	Vista vista;
	Modelo modelo;

	public Controlador(Vista v, Modelo m)
	{
		this.vista = v;
		this.modelo = m;
		vista.ventana.addWindowListener(this);
		vista.dlgMensaje.addWindowListener(this);
		vista.btnAnalizar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource().equals(vista.btnAnalizar))
		{
			if((vista.txtFuente.getText().length()!=0)&&(vista.txtDestino.getText().length()!=0))
			{
				vista.txaResultados.selectAll();
				vista.txaResultados.setText("");
				modelo.analizar(vista);
			}
			else
			{
				vista.lblMensaje.setText("Debes indicar los nombres de los ficheros");
				vista.dlgMensaje.setVisible(true);
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{}

	@Override
	public void windowClosed(WindowEvent arg0)
	{}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if(vista.ventana.isActive())
		{
			System.exit(0);
		}
		else
		{
			vista.dlgMensaje.setVisible(false);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{}

	@Override
	public void windowIconified(WindowEvent arg0)
	{}

	@Override
	public void windowOpened(WindowEvent arg0)
	{}
}
