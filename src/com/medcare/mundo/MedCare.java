package com.medcare.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MedCare {
	
	public final static String ruta ="";
	private ArrayList<Medico> medicos;
	private ArrayList<String> especialidades;
	private static MedCare instancia;
	
	public MedCare()
	{
		medicos = new ArrayList<Medico>();
		especialidades = new ArrayList<String>();
		cargarInfo();
	}
	
	public void cargarInfo()
	{
		try
		{
			BufferedReader bf = new BufferedReader(new FileReader(new File(ruta)));
			String s;
			while ((s = bf.readLine()) != null)
			{
				String[] info = s.split(":");
				Medico m = new Medico(info[1], info[0], info[5], info[4], info[2]);
				this.medicos.add(m);
			}
			bf.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
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

}
