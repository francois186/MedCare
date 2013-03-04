package com.medcare.activities;

import java.util.ArrayList;

import com.medcare.R;
import com.medcare.mundo.Medico;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaMedicoActivity extends Activity
{
	
	private ListView lista;
	private ArrayList<Medico> list;
	private boolean llamo;
	private String nombreMedicoSeleccionado;
	private String direccionMedicoSeleccionado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		llamo = false;
		setContentView(R.layout.activity_lista_medicos);
		lista = (ListView)findViewById(R.id.lista_medicos);
		cargarMedicos();
		lista.setClickable(true);
		lista.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) 
			{
				String[] t = ((TextView)view).getText().toString().split("\n");
				nombreMedicoSeleccionado = t[0];
				direccionMedicoSeleccionado = t[2];
				llamo = true;
				realizarLlamada(pos);	
			}
		});
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onResume() 
	{
		if(llamo == true)
		{
			showDialog("Agendar Cita", "¿Logró realizar la cita?\n¿Desea agendarla en su calendario?");
			llamo = false;
		}
		super.onResume();
	}
	
	public void cargarMedicos()
	{
		list = new ArrayList<Medico>();
		list.add(new Medico("Juan", "Bogotá", 2581685+"", "Cra 123 # 45-67", "Medico"));
		list.add(new Medico("Ana", "Bogotá", 2574136+"", "Cra 147 # 58-69", "Médico"));
		ArrayAdapter<Medico> adapter = new ArrayAdapter<Medico>(this, android.R.layout.simple_list_item_1, list);
		lista.setAdapter(adapter);
	}
	
	public void realizarLlamada(int pos)
	{
		try 
		{
			Medico med = list.get(pos);
	        Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(Uri.parse("tel:"+med.getTelefono()));
	        startActivity(callIntent);
	    } 
		catch (ActivityNotFoundException e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	public void showDialog(String title, String message)
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
			.setTitle(title)
			.setMessage(message)
			.setCancelable(true)
			.setPositiveButton("Sí", new DialogInterface.OnClickListener() 
			{
				@Override
				public void onClick(final DialogInterface dialog, final int which) 
				{
					if (nombreMedicoSeleccionado != null)
					{
						
						Intent i = new Intent(getBaseContext(), CitaActivity.class);
						i.putExtra("nombreMedico", nombreMedicoSeleccionado);
						i.putExtra("direccion", direccionMedicoSeleccionado);
						startActivity(i);
					}
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() 
			{
				@Override
				public void onClick(final DialogInterface dialog, final int which) 
				{
					dialog.cancel();
				}
			});
		alertDialog.create();
		alertDialog.show();
	}
	

}
