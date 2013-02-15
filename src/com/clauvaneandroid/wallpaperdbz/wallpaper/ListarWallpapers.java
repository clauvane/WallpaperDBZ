package com.clauvaneandroid.wallpaperdbz.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.clauvaneandroid.wallpaperdbz.R;
import com.clauvaneandroid.wallpaperdbz.menu.Dashboard;

public class ListarWallpapers extends Activity {

	TextView titulo;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wallpaper);

		titulo = (TextView) findViewById(R.id.titulo);
//		Typeface font = Typeface.createFromAsset(getAssets(), "font/fonte.TTF");  
//		titulo.setTypeface(font); 

		//Voltar Dashboard
		ImageView voltar = (ImageView) findViewById(R.id.logo_principal);
		voltar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ListarWallpapers.this,Dashboard.class));
			}
		}); 

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent it = new Intent(ListarWallpapers.this, WallpaperAmpliado.class);
				// Passa a posicao da imagem no griview
				String posicao = String.valueOf(position);  
				it.putExtra("posicao", posicao);
				startActivity(it);

			}
		});
	
	}
}