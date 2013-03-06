package com.medcare.activities;

import com.medcare.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class GuiaActivity extends Activity
{
	private String[] botones = {"Crisis de asma", "Hemorragia", "Intoxicamiento", "Ataques cardiovasculares",
			"Lesiones óseas o musculares", "Pérdida de conciencia", "Reacción alérgica", "Mordidas de animales",
			"Paro respiratorio", "Quemaduras", "Hipoglicemia"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guia);
		LinearLayout l = (LinearLayout)findViewById(R.id.layout_guia);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(15, 5, 15, 5);
		for (int i = 0; i < botones.length; i++) 
		{
			final Button b = new Button(this);
			b.setText(botones[i]);
			b.setLayoutParams(lp);
			b.setOnClickListener(new OnClickListener() 
			{
				@SuppressLint("DefaultLocale")
				@Override
				public void onClick(View v) 
				{
					Intent i = new Intent(getBaseContext(), ArticuloGuia.class);
					i.putExtra("cual", b.getText().toString().toLowerCase().replace(" ", "_"));
					startActivity(i);
				}
			});
			l.addView(b);
		}
	}
}
