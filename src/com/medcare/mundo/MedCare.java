package com.medcare.mundo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

public class MedCare extends Thread
{
	
	public final static String ruta = Environment.getExternalStorageDirectory()+"/guia_lista.txt";
	private static MedCare instancia;
	private ArrayList<Medico> medicos;
	private ArrayList<String> especialidades;
	private Context ctx;
	
	public MedCare()
	{
		medicos = new ArrayList<Medico>();
		especialidades = new ArrayList<String>();
	}

	public String[] darCiudades()
	{
		String [] resp = {"Todas","Arauca","Bogotá","Chía","Duitama","Florencia","Fusagasugá","Girardot","Ibagué","La Mesa","Madrid","Neiva","Sogamoso","Tauramena","Tunja","Villavicencio","Zipaquirá"};		
		return resp;
	}
	
	public ArrayList<String> darEspecialidades()
	{
		return especialidades;
	}
	
	public ArrayList<Medico> darMedicos(String nCiudad, String nEspecialidad)
	{
		ArrayList<Medico> resp = new ArrayList<Medico>();
		
		for (int i = 0; i < medicos.size(); i++) 
		{
			Medico med = medicos.get(i);
			if(nCiudad.equals("Todas"))
			{
				if(med.getEspecialidad().equals(nEspecialidad))
					resp.add(med);
			}
			else
			{
				if(med.getCiudad().equals(nCiudad) && med.getEspecialidad().equals(nEspecialidad))
					resp.add(med);				
			}
				
		}
		
		return resp;
	}
	
	public static MedCare darInstancia()
	{
		
		if(instancia == null)
		{
			instancia = new MedCare();
		}
		return instancia;
	}
	
	public ArrayList<Medico> getMedicos()
	{
		return medicos;
	}
	
	public void setContext(Context context)
	{
		ctx = context;
	}
	
	/**
	 * Método que carga en un hilo a parte lo médicos del directorio
	 * @pre: es necesario llamar primero el método setContext(), pasándole
	 * el contexto de la aplicación.
	 */
	@Override
	public void run()
	{
		try
		{
			//Se debe llamar primero setContext()
			AssetManager am = ctx.getAssets();
			InputStream is = am.open("guia_lista.txt");
			BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String s;
			while ((s = bf.readLine()) != null)
			{
				String[] info = s.split("%");
				Medico m = new Medico(info[1], info[0], info[5], info[4], info[2]);
				this.medicos.add(m);
			}
			is.close();
			bf.close();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void setEspecialidades()
	{
		String ultimaEsp = "";
		for (int i = 0; i < medicos.size(); i++)
		{
			String esp = medicos.get(i).getEspecialidad();
			if (esp.equals(ultimaEsp))
				continue;
			else
			{
				boolean esta = false;
				for (int j = 0; j < especialidades.size(); j++)
				{
					if (esp.equals(especialidades.get(j)))
					{
						esta = true;
						break;
					}
				}
				if (!esta)
				{
					ultimaEsp = esp;
					especialidades.add(esp);
				}
			}
		}
		java.util.Collections.sort(especialidades);
	}
}
