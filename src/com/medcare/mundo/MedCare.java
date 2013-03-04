package com.medcare.mundo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

public class MedCare 
{
	
	public final static String ruta = Environment.getExternalStorageDirectory()+"/guia_lista.txt";
	private ArrayList<Medico> medicos;
	private ArrayList<String> especialidades;
	private static MedCare instancia;
	
	public MedCare()
	{
		medicos = new ArrayList<Medico>();
		especialidades = new ArrayList<String>();
	}

	public void cargarInfo(Context context)
	{
		try
		{
			AssetManager am = context.getAssets();
			InputStream is = am.open("guia_lista.txt");
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			String s;
			while ((s = bf.readLine()) != null)
			{
				System.out.println(s);
				System.out.println("------------");
				String[] info = s.split("%");
				Medico m = new Medico(info[1], info[0], info[5], info[4], info[3]);
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
	
	public String[] darCiudades()
	{
		String [] resp = {"Arauca","Bogotá","Chía","Duitama","Florencia","Fusagasugá","Girardot","Ibagué","La Mesa","Madrid","Neiva","Sogamoso","Tauramena","Tunja","Villavicencio","Zipaquirá"};		
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
			if(med.getCiudad().equals(nCiudad) && med.getEspecialidad().equals(nEspecialidad))
				resp.add(med);
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

}
