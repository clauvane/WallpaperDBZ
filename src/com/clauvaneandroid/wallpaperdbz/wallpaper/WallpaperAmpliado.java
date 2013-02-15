package com.clauvaneandroid.wallpaperdbz.wallpaper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONArray;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.clauvaneandroid.wallpaperdbz.R;
import com.clauvaneandroid.wallpaperdbz.util.BitmapResized;

public class WallpaperAmpliado extends Activity{
	/** Called when the activity is first created. */

	private Integer[] wallpapers = {
			R.drawable.w1,
			R.drawable.w2,
			R.drawable.w3,
			R.drawable.w4,
			R.drawable.w5,
			R.drawable.w6,
			R.drawable.w7,
			R.drawable.w8,
			R.drawable.w9,
			R.drawable.w10,
			R.drawable.w11,
			R.drawable.w12,
			R.drawable.w13,
			R.drawable.w14,
			R.drawable.w15,
			R.drawable.w16,
			R.drawable.w17,
			R.drawable.w18,
			R.drawable.w19,
			R.drawable.w20,
			R.drawable.w21,
			R.drawable.w22,
			R.drawable.w23,
			R.drawable.w24,
			R.drawable.w25,		
			R.drawable.w26,
			R.drawable.w27,
			R.drawable.w28,
			R.drawable.w29,
			R.drawable.w30,
//			R.drawable.w31,
//			R.drawable.w32,
//			R.drawable.w33,
//			R.drawable.w34,
//			R.drawable.w35,
//			R.drawable.w36,
//			R.drawable.w37,
//			R.drawable.w38,
//			R.drawable.w39,
//			R.drawable.w40,
//			R.drawable.w41,
//			R.drawable.w42,	
	};
	int posicao = 0;
	int nota;
	int aux = R.drawable.w19;
	private ProgressDialog dialog;
	private Handler handler = new Handler();
	ListView listview;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb=null;
	ArrayList<NameValuePair> nameValuePairs;
	ArrayList<NameValuePair> nameValuePairsUpdate;
	TextView mural;
	String mural_texto;
	ImageView wallpaper;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wallpaperampliado);
		
		TextView titulo = (TextView) findViewById(R.id.titulo);
//		Typeface font = Typeface.createFromAsset(getAssets(), "font/fonte.TTF");  
//		titulo.setTypeface(font); 

		wallpaper = (ImageView) findViewById(R.id.logo);
		//Pega o atributo posicao da tela anterior
		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String position = extras.getString("posicao");
			try{
				posicao = Integer.parseInt(position);
				wallpaper.setImageBitmap(new BitmapResized(WallpaperAmpliado.this).getBitmap(wallpapers[posicao],300));
				aux = wallpapers[posicao];
			}
			catch(NumberFormatException e){
				Log.e("Erro","Erro ao converter" + position);
			}
		}



		//Previous
		Button antes = (Button) findViewById(R.id.anterior);
		antes.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(posicao==0){
					posicao = wallpapers.length;
				}
				posicao-= 1;
				wallpaper.setImageBitmap(new BitmapResized(WallpaperAmpliado.this).getBitmap(wallpapers[posicao],300));
				wallpaper.setTag(posicao);
				aux = wallpapers[posicao];
				getIntent().removeExtra("posicao");
				getIntent().putExtra("posicao", posicao+"");
			}
		});
		//Next
		Button depois = (Button) findViewById(R.id.next);
		depois.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(posicao == wallpapers.length-1)
				{
					posicao = -1;
				}
				posicao+= 1;
				wallpaper.setImageBitmap(new BitmapResized(WallpaperAmpliado.this).getBitmap(wallpapers[posicao],300));
				wallpaper.setTag(posicao);
				aux = wallpapers[posicao];
				getIntent().removeExtra("posicao");
				getIntent().putExtra("posicao", posicao+"");
			}

		});
		//Voltar Grid
		Button share = (Button) findViewById(R.id.share);
		share.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent sendIntent = new Intent(Intent.ACTION_SEND);
				//Mime type of the attachment (or) u can use sendIntent.setType("*/*")
				sendIntent.setType("image/jpeg");
				//Subject for the message or Email
				sendIntent.putExtra(Intent.EXTRA_SUBJECT, "I love Naruto!");
				//Full Path to the attachment
				sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://com.clauvaneandroid.wallpaperdbz/" + wallpapers[posicao]));
				//Use a chooser to decide whether email or mms
				startActivity(Intent.createChooser(sendIntent, "Email:"));
			}
		});


		//Home
		Button voltar = (Button) findViewById(R.id.actionbar_home);
		voltar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();				
			}
		}); 


		//Set wallpaper
		Button botao = (Button) findViewById(R.id.set);
		botao.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				dialog = ProgressDialog.show(WallpaperAmpliado.this,"Setting wallpaper", "Please wait...", false,true);
				vibrar();
				setWallpaper(aux);
			}
		});

	}

	public void setWallpaper(final int aux){
		new Thread() {
			@Override
			public void run() {
				Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(aux));
				try
				{
					getApplicationContext().setWallpaper(bitmap);
					handler.post(new Runnable(){
						public void run() {
							//Fecha a janela de progresso
							dialog.dismiss();
							Toast.makeText(getBaseContext(), "Wallpaper was put successfully!", Toast.LENGTH_LONG).show();
						}});
				} catch (IOException e)
				{
					handler.post(new Runnable(){
						public void run() {
							//Fecha a janela de progresso
							dialog.dismiss();
							Toast.makeText(getBaseContext(), "Error in set wallpaper!", Toast.LENGTH_LONG).show();
						}});
					e.printStackTrace();
				}
			}
		}.start();


	}

	public void vibrar(){
		Vibrator vibrator; 	
		vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
		vibrator.vibrate(300);

	}
}