package com.kb.mydemotea.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

public class BitmapUtil {

	/**
	 * 对图片进行二次采样，生成缩略图。放置加载过大图片出现内存溢出
	 */
	public static Bitmap createThumbnail(String filePath, int newWidth, int newHeight) {
		// 获取bitmapfactory去生成bitmap的格式对象
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 本次获取bitmap不分配内存，仅获取边界
		options.inJustDecodeBounds = true;
		// 开始进行本次采样（第一次）
		BitmapFactory.decodeFile(filePath, options);
		// 采样结束后，获取到了filePath的宽高
		int originalWidth = options.outWidth;
		int originalHeight = options.outHeight;
		// 计算要显示的宽高和文件本身的宽高的比例值
		int ratioWidth = originalWidth / newWidth;
		int ratioHeight = originalHeight / newHeight;
		// 把比例值中较大的交给options
		options.inSampleSize = ratioHeight > ratioWidth ? ratioHeight
				: ratioWidth;
		// 设置第二次采样时的像素点的格式
		options.inPreferredConfig = Config.RGB_565;
		// 设置第二次采样时，分配内存
		options.inJustDecodeBounds = false;
		// 进行第二次采样，把采样结果返回。
		return BitmapFactory.decodeFile(filePath, options);
	}

	public static  Bitmap createThumbnail(Resources res, int id, int newWidth,
			int newHeight) {
		// 获取bitmapfactory去生成bitmap的格式对象
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 本次获取bitmap不分配内存，仅获取边界
		options.inJustDecodeBounds = true;
		// 开始进行本次采样（第一次）
		BitmapFactory.decodeResource(res, id, options);
		// 采样结束后，获取到了filePath的宽高
		int originalWidth = options.outWidth;
		int originalHeight = options.outHeight;
		// 计算要显示的宽高和文件本身的宽高的比例值
		int ratioWidth = originalWidth / newWidth;
		int ratioHeight = originalHeight / newHeight;
		// 把比例值中较大的交给options
		options.inSampleSize = ratioHeight > ratioWidth ? ratioHeight
				: ratioWidth;
		// 设置第二次采样时的像素点的格式
		options.inPreferredConfig = Config.RGB_565;
		// 设置第二次采样时，分配内存
		options.inJustDecodeBounds = false;
		// 进行第二次采样，把采样结果返回。
		return BitmapFactory.decodeResource(res, id, options);
	}
}
