package com.kb.mydemotea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.kb.mydemotea.adapter.MyAdapter;
import com.kb.mydemotea.dao.StudentDao;
import com.kb.mydemotea.domain.Student;

/**
 * 我的收藏页+历史记录
 * 
 * @author Administrator
 * 
 */
public class CollectActivity extends Activity {
	StudentDao studentDao = new StudentDao(this);
	List<Student> students;
	private List<Map<String, Object>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collect);
		Intent intent = getIntent();
		String tableName = intent.getStringExtra("tableName");

		list = new ArrayList<Map<String, Object>>();
		// 从数据库读取信息
		students = studentDao.findAll(tableName);
		if (students != null) {
			Log.i("collectActivity-->", students.toString());

			// 转换数据
			for (int i = 0; i < students.size(); i++) {
				Student student = students.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("newsid", student.getNewsid());
				map.put("title", student.getTitle());
				map.put("source", student.getSource());
				map.put("description", student.getDescription());
				map.put("wap_thumb", student.getWap_thumb());
				map.put("create_time", student.getCreate_time());
				map.put("nickname", student.getNickname());
				list.add(map);
			}
			Log.i("collectactivity--->", list.toString());
		}
		// 创建ListView
		ListView listView = (ListView) findViewById(R.id.lv_collect);
		MyAdapter adapter = new MyAdapter(this);
		adapter.setList(list);
		listView.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.collect, menu);
		return true;
	}

}
