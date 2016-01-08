package com.kb.mydemotea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.kb.mydemotea.asynctask.LoadWebAsyncTask;
import com.kb.mydemotea.config.Config;
import com.kb.mydemotea.dao.StudentDao;
import com.kb.mydemotea.domain.Student;

public class WebActivity extends Activity {
	private TextView title_web;
	private TextView source_web;
	private TextView create_time_web;
	private WebView webView;
	private String myid;
	// 进去前条目信息
	private int newsid;
	private String title;
	private String source;
	private String description;
	private String wap_thumb;
	private String create_time;
	private String nickname;

	// 数据库操作
	StudentDao studentDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		studentDao = new StudentDao(this);

		title_web = (TextView) findViewById(R.id.title_web);
		source_web = (TextView) findViewById(R.id.source_web);
		create_time_web = (TextView) findViewById(R.id.create_time_web);

		webView = (WebView) findViewById(R.id.wv_web);

		// 设置webview对象支持解析javascript语句
		webView.getSettings().setJavaScriptEnabled(true);

		// 支持alert（）等特殊的javascript语句
		webView.setWebChromeClient(new WebChromeClient());

		// 如果不使用此代码设置此属性，就会跳入系统的浏览器中
		webView.setWebViewClient(new WebViewClient());

		Intent intent = getIntent();
		myid = intent.getStringExtra("myid");
		// String myid = 8195 + "";
		String url = Config.NEWCONTENTPATH + myid;
		new LoadWebAsyncTask(this, title_web, source_web, create_time_web,
				webView).execute(url);
		// 获取信息
		newsid = Integer.parseInt(myid);
		title = intent.getStringExtra("title");
		source = intent.getStringExtra("source");
		description = intent.getStringExtra("description");
		wap_thumb = intent.getStringExtra("wap_thumb");
		create_time = intent.getStringExtra("create_time");
		nickname = intent.getStringExtra("nickname");
		Log.i("tag---->", newsid + "--" + title + "--" + source + "--"
				+ description + "--" + wap_thumb + "--" + create_time + "--"
				+ nickname);

		history();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web, menu);
		return true;
	}

	public void onWebClick(View view) {
		switch (view.getId()) {
		case R.id.iv_back_web:// 返回
			finish();
			break;
		case R.id.iv_collect_web:// 收藏

			Student stu = new Student(newsid, title, source, wap_thumb,
					create_time, nickname);

			if (!studentDao.isExist(newsid, "test1")) {// 不存在
				studentDao.insert(stu, "test1");
				Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();

			} else {// 存在
				studentDao.delete(newsid, "test1");
				Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();

			}
			break;
		case R.id.iv_share_web:// 分享
			Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();

			break;

		default:
			break;
		}
	}

	/**
	 * 记录浏览历史
	 */
	public void history() {
		Student stu = new Student(newsid, title, source, wap_thumb,
				create_time, nickname);
		studentDao.insert(stu, "test2");

	}
}
