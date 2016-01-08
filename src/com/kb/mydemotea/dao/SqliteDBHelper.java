package com.kb.mydemotea.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * ���ݿ⹤����
 * 
 * @author Administrator
 * 
 */
public class SqliteDBHelper extends SQLiteOpenHelper {

	public SqliteDBHelper(Context context) {
		super(context, "student.db", null, 1); // �汾�Ǵ��ڵ���1��������
		// TODO Auto-generated constructor stub
	}

	// public SQLiteOpenHelper(){
	// super();//
	// }

	/**
	 * �������ݿ� (ִֻ��һ�Σ�һ�����ݿ���ڣ�����ִ��)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// test1:�ղ�
		// test2:��ʷ��¼
		db.execSQL("create table if not exists test1(id integer primary key autoincrement,newsid integer,title varchar(10),source verchar(30),description varchar(30),wap_thumb varchar(30),create_time varchar(20),nickname varchar(10))");
		db.execSQL("create table if not exists test2(id integer primary key autoincrement,newsid integer,title varchar(10),source verchar(30),description varchar(30),wap_thumb varchar(30),create_time varchar(20),nickname varchar(10))");
		// db.close();
		// ��Ҫ�ر����ݿ�
		Log.i("SqliteDBHelper", "-----------onCreateִ����");
	}

	/**
	 * �������ݿ�
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists test1");// ɾ��
		db.execSQL("drop table if exists test2");// ɾ��

		onCreate(db);// �ٵ���onCreate����
		Log.i("SqliteDBHelper", "-----------onUpgradeִ���� oldVersion"
				+ oldVersion + "  newVersion" + newVersion);

	}

}
