package com.clauvaneandroid.wallpaperdbz.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapResized {
	Context context;
	public BitmapResized(Context context){
		this.context = context;
	}
	
	public Bitmap getBitmap(int wallpaper, int tamanho){		
		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		onlyBoundsOptions.inJustDecodeBounds = true;
		onlyBoundsOptions.inDither=true;//optional
		onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
		BitmapFactory.decodeResource(context.getResources(), wallpaper ,onlyBoundsOptions);
		if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
			return null;

		int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

		double ratio = (originalSize > tamanho) ? (originalSize / tamanho) : 1.0;

		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
		bitmapOptions.inDither=true;//optional
		bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), wallpaper ,bitmapOptions);		
		return bitmap;
	}
	
	public Bitmap getBitmap(InputStream input, int tamanho) throws FileNotFoundException, IOException{
		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		onlyBoundsOptions.inJustDecodeBounds = true;
		onlyBoundsOptions.inDither=true;//optional
		onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
		BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
		input.close();
		if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
			return null;

		int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

		double ratio = (originalSize > tamanho) ? (originalSize / tamanho) : 1.0;

		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
		bitmapOptions.inDither=true;//optional
		bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
		Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
		input.close();
		return bitmap;
	}

	private static int getPowerOfTwoForSampleRatio(double ratio){
		int k = Integer.highestOneBit((int)Math.floor(ratio));
		if(k==0) return 1;
		else return k;
	}	

	
}
