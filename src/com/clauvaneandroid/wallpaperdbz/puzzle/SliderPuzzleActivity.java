package com.clauvaneandroid.wallpaperdbz.puzzle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.clauvaneandroid.wallpaperdbz.R;
import com.clauvaneandroid.wallpaperdbz.util.BitmapResized;

public class SliderPuzzleActivity extends Activity {
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
		final Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String position = extras.getString("posicao");
			try{
				posicao = Integer.parseInt(position);
				Bitmap bm = (new BitmapResized(SliderPuzzleActivity.this).getBitmap(wallpapers[posicao],300));
				GameboardView.imgPuzzle = bm;
			}
			catch(NumberFormatException e){
				Log.e("Erro","Erro ao converter" + position);
			}
		}
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle);
    }
}