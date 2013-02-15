package com.clauvaneandroid.wallpaperdbz.contato;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.clauvaneandroid.wallpaperdbz.R;

public class Contato extends Activity{
	TextView titulo;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contato);
		final EditText nome = (EditText) findViewById(R.id.nome);
		final EditText mensagem = (EditText) findViewById(R.id.mensagem);
	

		titulo = (TextView) findViewById(R.id.titulo);
//		Typeface font = Typeface.createFromAsset(getAssets(), "font/fonte.TTF");  
//		titulo.setTypeface(font); 
		
		Button enviar = (Button) findViewById(R.id.enviar);
		enviar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if(!nome.getText().toString().equals("") && !mensagem.getText().toString().equals("")){
				email(nome.getText().toString(),mensagem.getText().toString());
				}
				else{
					Toast.makeText(getBaseContext(), "The name field or the message field are empty." ,Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	//Metodo E-mail

	public void email(String nome, String mensagem){
		String emailtext = new String();
		emailtext = "Name: "+ nome+ "\n Message: "+mensagem;
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"clauvane@gmail.com"});
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Support from Naruto Live Wallpaper");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext);
		startActivity(Intent.createChooser(emailIntent, "Send mail...")); 
	}

}