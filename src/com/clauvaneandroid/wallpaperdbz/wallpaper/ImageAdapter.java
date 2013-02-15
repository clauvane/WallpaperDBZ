package com.clauvaneandroid.wallpaperdbz.wallpaper;

import com.clauvaneandroid.wallpaperdbz.R;
import com.clauvaneandroid.wallpaperdbz.util.BitmapResized;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return wallpapers.length;
	}

	public Object getItem(int position) {
		return wallpapers[position];
	}

	public long getItemId(int position) {
		return position;
	}
	
	int tamanhoWallpaper = 0;
	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		//Pega tamanho do display
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();	
		int width = display.getWidth();
		tamanhoWallpaper = (int) width/3;
		ImageView imageView;
		if (convertView == null) {  // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(tamanhoWallpaper, tamanhoWallpaper));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(1, 1, 1, 1);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageBitmap(new BitmapResized(mContext).getBitmap(wallpapers[position],160));
		return imageView;
	}

	// references to our images
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
}


