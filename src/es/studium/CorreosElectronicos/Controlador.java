package es.studium.CorreosElectronicos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controlador implements WindowListener, ActionListener
{
	Vista vista;
	Modelo modelo;

	public Controlador(Vista v, Modelo m)
	{
		vista = v;
		modelo = m;
		vista.ventana.addWindowListener(this);
		vista.dlgMensaje.addWindowListener(this);
		vista.btnAnalizar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource().equals(vista.btnAnalizar))
		{
			// Leer el fichero "correoselectronicos.txt"
			// Analizar los correos
			// Guardar en "correctos.txt"
			Pattern correos;
			Matcher m;
			correos = Pattern.compile("[a-z0-9._]{2,126}@[a-z0-9.]{2,126}.[a-z0-9]{2,126}");

			// Leer fichero
			try
			{
				//Origen de los datos en el proyecto anterior
				FileReader fr = new FileReader(vista.txtRuta.getText());
				//Buffer de lectura
				BufferedReader entrada = new BufferedReader(fr);
				// Destino de los datos
				FileWriter fw = new FileWriter("correctos.txt", true);
				// Buffer de escritura
				BufferedWriter bw = new BufferedWriter(fw);
				// Objeto para la escritura
				PrintWriter salida = new PrintWriter(bw);

				String s;
				//Bucle para sacar la información del archivo
				while((s=entrada.readLine())!=null)
				{
					vista.txaResultados.append(s);
					m = correos.matcher(s);
					if(!m.matches())
					{
						vista.txaResultados.append("-->Incorrecto");
					}
					else
					{
						vista.txaResultados.append("-->Correcto");
						salida.println(s);
					}
					vista.txaResultados.append("\n");
				}
				vista.lblMensaje.setText("Proceso finalizado");
				//Cerrar el objeto entrada
				entrada.close();
				fr.close();
				//Cerrar el objeto salida, el objeto bw y el fw
				salida.close();
				bw.close();
				fw.close();
			}
			catch(FileNotFoundException e)
			{
				vista.lblMensaje.setText("Archivo NO encontrado");
			}
			catch(IOException i)
			{
				vista.lblMensaje.setText("Se produjo un error de Archivo");
			}
			finally
			{
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
