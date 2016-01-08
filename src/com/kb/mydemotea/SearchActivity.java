package com.kb.mydemotea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.mydemotea.adapter.MyAdapter;
import com.kb.mydemotea.asynctask.LoadDataAsyncTask;
import com.kb.mydemotea.config.Config;

public class SearchActivity extends Activity {
	private ListView listView;
	private MyAdapter adapter;
	private TextView tv_key;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		listView = (ListView) findViewById(R.id.lv_search);
		adapter = new MyAdapter(this);
		listView.setAdapter(adapter);
		tv_key = (TextView) findViewById(R.id.tv_key);

		Intent intent = getIntent();
		String keyWord = intent.getStringExtra("keyWord");

		new LoadDataAsyncTask(listView, adapter, this)
				.execute(Config.SEARCHPATH + "&rows=10&page=1&search="
						+ keyWord);
		tv_key.setText(keyWord);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public void onBackClick(View view) {
		finish();
	}
}
