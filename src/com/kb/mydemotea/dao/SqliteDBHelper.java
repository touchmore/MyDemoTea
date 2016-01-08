package com.kb.mydemotea.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库工具类
 * 
 * @author Administrator
 * 
 */
public class SqliteDBHelper extends SQLiteOpenHelper {

	public SqliteDBHelper(Context context) {
		super(context, "student.db", null, 1); // 版本是大于等于1的正整数
		// TODO Auto-generated constructor stub
	}

	// public SQLiteOpenHelper(){
	// super();//
	// }

	/**
	 * 创建数据库 (只执行一次，一旦数据库存在，则不再执行)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// test1:收藏
		// test2:历史记录
		db.execSQL("create table if not exists test1(id integer primary key autoincrement,newsid integer,title varchar(10),source verchar(30),description varchar(30),wap_thumb varchar(30),create_time varchar(20),nickname varchar(10))");
		db.execSQL("create table if not exists test2(id integer primary key autoincrement,newsid integer,title varchar(10),source verchar(30),description varchar(30),wap_thumb varchar(30),create_time varchar(20),nickname varchar(10))");
		// db.close();
		// 不要关闭数据库
		Log.i("SqliteDBHelper", "-----------onCreate执行了");
	}

	/**
	 * 升级数据库
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists test1");// 删除
		db.execSQL("drop table if exists test2");// 删除

		onCreate(db);// 再调用onCreate创建
		Log.i("SqliteDBHelper", "-----------onUpgrade执行了 oldVersion"
				+ oldVersion + "  newVersion" + newVersion);

	}

}
