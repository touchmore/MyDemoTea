package com.kb.mydemotea.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.kb.mydemotea.utils.HttpURLConnHelper;
import com.kb.mydemotea.utils.SDCardHelper;

/**
 * “Ï≤Ω»ŒŒÒ£¨«Î«ÛÕº∆¨ ˝æ›£¨º”‘ÿÕº∆¨
 * 
 * @author Administrator
 * 
 */
public class LoadImageAsyncTask extends AsyncTask<String, Void, byte[]> {
	private Context context;
	private ImageView imageView;
	private String name;

	public LoadImageAsyncTask(Context context, ImageView imageView, String name) {
		super();
		this.context = context;
		this.imageView = imageView;
		this.name = name;
	}

	@Override
	protected byte[] doInBackground(String... params) {
		// TODO Auto-generated method stub

		return HttpURLConnHelper.loadByteFromURL(params[0]);
	}

	@Override
	protected void onPostExecute(byte[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0,
					result.length);
			imageView.setImageBitmap(bitmap);
			imageView.setScaleType(ScaleType.FIT_XY);
			if (name != null) {
				// ±£¥ÊÕº∆¨
				SDCardHelper.saveFileToSDCardPublicDir(result,
						Environment.DIRECTORY_DOWNLOADS, name);
			}
		} else {
			// Toast.makeText(context, "Õ¯¬Á“Ï≥££¨Õº∆¨º”‘ÿ ß∞‹",
			// Toast.LENGTH_SHORT).show();

		}

	}
}
