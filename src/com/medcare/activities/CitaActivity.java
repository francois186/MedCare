package com.medcare.activities;

import java.util.Calendar;

import com.medcare.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;

public class CitaActivity extends Activity{
	
	private Calendar c;
	private String nombreMedico;
	private String direccion;
	private TimePicker tp; 
	private int anio;
	private int mes;
	private int dia;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		c = Calendar.getInstance();
		Bundle ex = getIntent().getExtras();
		nombreMedico = ex.getString("nombreMedico");
		direccion = ex.getString("direccion");
		setContentView(R.layout.activity_agendar_cita);
		
		DatePicker dp = (DatePicker)findViewById(R.id.fecha_crear_calendario);
		tp = (TimePicker)findViewById(R.id.tiempo_crear_calendario);
		
		//Inicialización del calendario y hora con las actuales
		dp.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener()
		{
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
			{
				anio = year;
				mes = monthOfYear;
				dia = dayOfMonth;
			}
		});
		tp.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
		tp.setCurrentMinute(c.get(Calendar.MINUTE));
		
		super.onCreate(savedInstanceState);
	}
	
	public void crearEventoCalendario(View v)
	{
		Calendar x = Calendar.getInstance();
		x.set(anio, mes, dia, tp.getCurrentHour(), tp.getCurrentMinute(), 0);
		long inic = c.getTimeInMillis();
		
		Intent intent = new Intent(Intent.ACTION_EDIT)
	    	.setType("vnd.android.cursor.item/event")
	    	.putExtra("dtstart", inic)
	    	.putExtra("title", "Cita con el médico "+nombreMedico)
	    	.putExtra("eventLocation", direccion)
	    	.putExtra("organizer", "MedCare")
			.putExtra("duration", "PT1H");
	    startActivity(intent);
	}
}
