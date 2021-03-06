package com.medcare.activities;


import com.medcare.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity 
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton directorio = (ImageButton)findViewById(R.id.butt_directorio);
		directorio.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(getBaseContext(), DirectorioActivity.class);		
				startActivity(i);
			}
		});
		
		ImageButton guia = (ImageButton)findViewById(R.id.butt_guia);
		guia.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(getBaseContext(), GuiaActivity.class);
				startActivity(i);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
