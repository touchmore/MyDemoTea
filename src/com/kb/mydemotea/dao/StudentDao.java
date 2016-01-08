package com.kb.mydemotea.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kb.mydemotea.domain.Student;

public class StudentDao {

	/**
	 * 添加
	 * 
	 * @param stu
	 * @return
	 */

	private SqliteDBHelper dbHelper;

	public StudentDao(Context context) {
		// TODO Auto-generated constructor stub
		dbHelper = new SqliteDBHelper(context);
	}

	public boolean insert(Student stu, String tableName) {
		try {
			// SQLiteDatabase db = SqliteDBHelper.getSqliteDataBase();
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			db.execSQL(
					"insert into "
							+ tableName
							+ "(newsid,title,source,description,wap_thumb,create_time,nickname) values(?,?,?,?,?,?,?)",
					new Object[] { stu.getNewsid(), stu.getTitle(),
							stu.getSource(), stu.getSource(),
							stu.getWap_thumb(), stu.getCreate_time(),
							stu.getNickname() });
			db.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * 删除
	 * 
	 * @param stuNo
	 * @return
	 */
	public boolean delete(int newsid, String tableName) {
		try {
			// SQLiteDatabase db = SqliteDBHelper.getSqliteDataBase();
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			db.execSQL("delete from " + tableName + "  where newsid=?",
					new Object[] { newsid });
			db.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// /**
	// * 更新
	// *
	// * @param stu
	// * @return
	// */
	// public boolean update(Student stu) {
	// try {
	// // SQLiteDatabase db = SqliteDBHelper.getSqliteDataBase();
	// SQLiteDatabase db = dbHelper.getReadableDatabase();
	// db.execSQL(
	// "update student set stuName=?,gender=?,money=?,address=? where stuNo=?",
	// new Object[] { stu.getStuName(), stu.getGender(),
	// stu.getMoney(), stu.getAddress(), stu.getStuNo() });
	// db.close();
	// return true;
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// return false;
	// }

	/**
	 * 根据学号获取学生信息
	 * 
	 * @param stuNo
	 * @return
	 */
	public Student findByNo(int newsid, String tableName) {
		SQLiteDatabase db = null;
		try {
			// db = SqliteDBHelper.getSqliteDataBase();
			db = dbHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from " + tableName
					+ " where newsid=?", new String[] { newsid + "" });
			Student student = new Student();
			if (cursor.moveToNext()) {
				student.setNewsid(newsid);
				student.setTitle(cursor.getString(cursor
						.getColumnIndex("title")));
				student.setSource(cursor.getString(cursor
						.getColumnIndex("source")));
				student.setDescription(cursor.getString(cursor
						.getColumnIndex("description")));
				student.setWap_thumb(cursor.getString(cursor
						.getColumnIndex("wap_thumb")));
				student.setCreate_time(cursor.getString(cursor
						.getColumnIndex("create_time")));
				student.setNickname(cursor.getString(cursor
						.getColumnIndex("nickname")));

			}

			return student;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			db.close();
		}
		return null;
	}

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<Student> findAll(String tableName) {
		SQLiteDatabase db = null;
		List<Student> data = new ArrayList<Student>();
		try {
			// db = SqliteDBHelper.getSqliteDataBase();
			db = dbHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from " + tableName, null);
			while (cursor.moveToNext()) {
				Student student = new Student();
				student.setNewsid(cursor.getInt(cursor.getColumnIndex("newsid")));
				student.setTitle(cursor.getString(cursor
						.getColumnIndex("title")));
				student.setSource(cursor.getString(cursor
						.getColumnIndex("source")));
				student.setDescription(cursor.getString(cursor
						.getColumnIndex("description")));
				student.setWap_thumb(cursor.getString(cursor
						.getColumnIndex("wap_thumb")));
				student.setCreate_time(cursor.getString(cursor
						.getColumnIndex("create_time")));
				student.setNickname(cursor.getString(cursor
						.getColumnIndex("nickname")));
				data.add(student);
			}

			return data;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			db.close();
		}
		return null;
	}

	/**
	 * 查询是否存在某条数据
	 */

	public boolean isExist(int newsid, String tableName) {
		SQLiteDatabase db = null;
		List<Student> data = new ArrayList<Student>();

		// db = SqliteDBHelper.getSqliteDataBase();
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + tableName
				+ "  where newsid = ? ", new String[] { newsid + "" });

		if (cursor.getCount() != 0) {
			Log.i("StudentDao", cursor.getCount() + "");
			return true;

		}

		return false;

	}
}
