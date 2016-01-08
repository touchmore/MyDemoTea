package com.kb.mydemotea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.mydemotea.adapter.MyAdapter;
import com.kb.mydemotea.asynctask.LoadDataAsyncTask;
import com.kb.mydemotea.config.Config;

public class SearchActivity extends Activity {
	private ListView listView;
	private MyAdapter adapter;
	private TextView tv_key;

	// 上拉加载下一页
	private boolean isBottom = false;
	private int currentIndex = 1;
	private String keyWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		listView = (ListView) findViewById(R.id.lv_search);
		adapter = new MyAdapter(this);
		listView.setAdapter(adapter);
		tv_key = (TextView) findViewById(R.id.tv_key);

		Intent intent = getIntent();
		keyWord = intent.getStringExtra("keyWord");

		new LoadDataAsyncTask(listView, adapter, this)
				.execute(Config.SEARCHPATH + "&rows=10&page=1&search="
						+ keyWord);

		tv_key.setText(keyWord);

		// 添加滚动监听
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view,// listview
					int scrollState// 滚动状态
			) {
				// TODO Auto-generated method stub
				if (isBottom && scrollState == SCROLL_STATE_IDLE) {
					// 加载下一页
					currentIndex++;
					new LoadDataAsyncTask(listView, adapter,
							SearchActivity.this).execute(Config.SEARCHPATH
							+ "&rows=10&page=" + currentIndex + "&search="
							+ keyWord);

				}
			}

			@Override
			public void onScroll(AbsListView view,// listview
					int firstVisibleItem, // 第一个显示的条目的位置
					int visibleItemCount, // 可见的条目个数
					int totalItemCount // 一共多少个条目
			) {
				// TODO Auto-generated method stub
				if (firstVisibleItem + visibleItemCount == totalItemCount) {// 滑到底部
					isBottom = true;
				} else {
					isBottom = false;
				}

			}
		});

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
