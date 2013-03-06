package com.medcare.mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;

public class Guia 
{
	
	private String titulo;
	private String contenido;
	private String rutaImagen;
	private Context ctx;
	private static Guia instancia;
	
	public Guia(String titulo) 
	{
		super();
		this.titulo = titulo;
		contenido = "";
		
	}
	
	public String getTitulo() 
	{
		return titulo;
	}
	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}
	public String getContenido() 
	{
		return contenido;
	}
	public void setContenido(String contenido) 
	{
		this.contenido = contenido;
	}
	public String getRutaImagen() 
	{
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) 
	{
		this.rutaImagen = rutaImagen;
	}
	
	public void setContext(Context context)
	{
		ctx = context;
	}
	
	public void llenarContenido()
	{
		try 
		{
			AssetManager am = ctx.getAssets();
			InputStream is = am.open(titulo+".txt");
			BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String s = "";
			while ((s = bf.readLine()) != null)
			{
				contenido += s+"\n";
			}
			is.close();
			bf.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Guia darInstancia(String titulo)
	{
		if(instancia == null)
			instancia = new Guia(titulo);
		return instancia;
	}
	

}
