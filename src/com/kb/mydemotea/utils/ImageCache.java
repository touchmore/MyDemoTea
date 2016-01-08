package com.kb.mydemotea.utils;

import java.io.File;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;

public class ImageCache {
	private ImageCache() {

	}

	private static ImageCache imageCache = null;

	public static ImageCache getImageCache() {
		if (imageCache == null) {
			imageCache = new ImageCache();
		}
		return imageCache;
	}

	// maxSize是内存缓冲区的大�?�?��而言我们把应用程序所占内存的1/8，作为图片的缓冲区域
	private MyLruCache myLruCache = new MyLruCache((int) (Runtime.getRuntime()
			.maxMemory() / 8));

	// 这是内存区域管理的类
	class MyLruCache extends LruCache<String, Bitmap> {

		// 这是构�?方法
		public MyLruCache(int maxSize) {
			super(maxSize);
			// TODO Auto-generated constructor stub
		}

		// 这是计算每一个bitmap大小的方�?
		@Override
		protected int sizeOf(String key, Bitmap value) {
			// TODO Auto-generated method stub
			return value.getByteCount();
		}

	}

	public Bitmap getBitmap(String url, int width, int height) {
		// 试图从内存中获取到bitmap对象
		Bitmap bitmap = myLruCache.get(url);
		String name = MD5Util.getMD5(url);
		File file = new File(
				SDCardHelper
						.getSDCardPublicDir(Environment.DIRECTORY_DOWNLOADS)
						+ File.separator + name);
		if (bitmap != null) {
			Log.i("在内存中", "找到了");
			return bitmap;
		} else {
			if (file.exists()) {
				Log.i("在SD卡中", "找到了");
				// 找到SD卡的图片了，就把找到的文件以二次采样的方式获取到
				bitmap = BitmapUtil.createThumbnail(file.getAbsolutePath(),
						width, height);
				// 把加载到的bitmap放入内存
				myLruCache.put(url, bitmap);
				return bitmap;
			} else {
				Log.i("在SD卡中", "找到了");
				byte[] data = HttpURLConnHelper.loadByteFromURL(url);
				SDCardHelper.saveFileToSDCardPublicDir(data,
						Environment.DIRECTORY_DOWNLOADS, name);
				// 请求完网络了，就把请求到的文件以二次采样的方式获取到
				bitmap = BitmapUtil.createThumbnail(file.getAbsolutePath(),
						width, height);
				// 把小图片放入内存
				myLruCache.put(url, bitmap);
				return bitmap;
			}

		}

	}

}
