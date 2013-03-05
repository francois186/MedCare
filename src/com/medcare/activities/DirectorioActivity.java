package com.medcare.activities;

import com.medcare.R;
import com.medcare.mundo.MedCare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DirectorioActivity extends Activity
{
	
	private MedCare medcare;
	private Spinner spCiudades;
	private Spinner spEspecialidades;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		medcare = MedCare.darInstancia();
		cargar();
		setContentView(R.layout.activity_directorio);
		if (medcare.isAlive())
		{
			try
			{
				wait(500);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		medcare.setEspecialidades();
		inicializarDatos();
		super.onCreate(savedInstanceState);
	}
	
	public void cargar()
	{
		medcare.setContext(this);
		medcare.start();
	}
	
	public void pasarVista(View v)
	{
		Intent i = new Intent(this, ListaMedicoActivity.class);
		startActivity(i);
	}

	private void inicializarDatos()
	{
		spCiudades = (Spinner)findViewById(R.id.sp_ciudad);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, medcare.darCiudades());
		spCiudades.setAdapter(adapter);
		spEspecialidades = (Spinner)findViewById(R.id.sp_especialidad);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, medcare.darEspecialidades());
		spEspecialidades.setAdapter(adapter);
	}
}
