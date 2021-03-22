package es.studium.CorreosElectronicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Modelo
{
	public void analizar(Vista vista)
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
			FileReader fr = new FileReader(vista.txtFuente.getText());
			//Buffer de lectura
			BufferedReader entrada = new BufferedReader(fr);
			// Destino de los datos
			FileWriter fw = new FileWriter(vista.txtDestino.getText(), true);
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
