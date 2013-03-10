package com.medcare.activities;

import com.medcare.R;
import com.medcare.mundo.Guia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ArticuloGuia extends Activity 
{
	private String tipo;
	private Guia guia;
	
	@SuppressLint("DefaultLocale")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		tipo = getIntent().getExtras().getString("cual");
		guia = Guia.darInstancia(tipo);
		String t = tipo.toUpperCase().replace("_", " ");
		guia.setTitulo(tipo);
		
		setContentView(R.layout.activity_articulo_guia);
		
		LinearLayout l = (LinearLayout)findViewById(R.id.layout_articulo);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(15, 5, 15, 5);
		
		final TextView titutlo = new TextView(this);
		titutlo.setTextSize(20);
		titutlo.setLayoutParams(lp);
		titutlo.setText(t);
		titutlo.setGravity(Gravity.CENTER);
		
		ImageView imagen = new ImageView(this);
		int id = getResources().getIdentifier(tipo, "drawable", getPackageName());
		imagen.setImageDrawable(getResources().getDrawable(id));
		
		cargar();
		
		final TextView contenido = new TextView(this);
		contenido.setLayoutParams(lp);
		contenido.setText(guia.getContenido());
		contenido.setTextSize(15);
		guia.setContenido("");
		
		l.addView(titutlo);
		l.addView(contenido);
		l.addView(imagen);
		super.onCreate(savedInstanceState);
	}
	
	public void cargar()
	{
		guia.setContext(this);
		guia.llenarContenido();
	}
	
}
