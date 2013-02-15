package com.clauvaneandroid.wallpaperdbz.menu;

//import com.amor.codfans.R;

import com.clauvaneandroid.wallpaperdbz.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Bemvindo extends Activity{
	boolean primeira;
	private static final String NOME = "FanClub";
	TextView nome;
	LinearLayout nome_layout;
	LinearLayout hello_layout;
	Handler handler = new Handler();
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.bemvindo);
		nome = (TextView) findViewById(R.id.nome);
		nome_layout = (LinearLayout) findViewById(R.id.name_layout);
		hello_layout = (LinearLayout) findViewById(R.id.hello_layout);

		//Go
		final Button go = (Button) findViewById(R.id.go);
		go.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				if(!nome.getText().toString().equals("")){
					salvarPrimeiraVisita();
					salvarPerfil(nome.getText().toString());
					finish();
					startActivity(new Intent(Bemvindo.this,Dashboard.class));
				}
				else{
					Toast.makeText(Bemvindo.this,"Please, tell us what's your name! =D", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	public void salvarPrimeiraVisita(){
		SharedPreferences pref = getSharedPreferences(NOME, 0);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("primeira",true);
		editor.commit();
	}

	public void salvarPerfil(String pPerfil){
		//Salva o contador nas prefer�ncias
		SharedPreferences pref = getSharedPreferences(NOME, MODE_PRIVATE);
		//Abre a prefer�ncia para edi��o
		SharedPreferences.Editor editor = pref.edit();
		//Atualiza o valor do contador
		editor.putString("nome", pPerfil);
		//Faz commit para salvar os dados
		editor.commit();
	}
}