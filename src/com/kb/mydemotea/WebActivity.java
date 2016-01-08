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
	// ��ȥǰ��Ŀ��Ϣ
	private int newsid;
	private String title;
	private String source;
	private String description;
	private String wap_thumb;
	private String create_time;
	private String nickname;

	// ���ݿ����
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

		// ����webview����֧�ֽ���javascript���
		webView.getSettings().setJavaScriptEnabled(true);

		// ֧��alert�����������javascript���
		webView.setWebChromeClient(new WebChromeClient());

		// �����ʹ�ô˴������ô����ԣ��ͻ�����ϵͳ���������
		webView.setWebViewClient(new WebViewClient());

		Intent intent = getIntent();
		myid = intent.getStringExtra("myid");
		// String myid = 8195 + "";
		String url = Config.NEWCONTENTPATH + myid;
		new LoadWebAsyncTask(this, title_web, source_web, create_time_web,
				webView).execute(url);
		// ��ȡ��Ϣ
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
		case R.id.iv_back_web:// ����
			finish();
			break;
		case R.id.iv_collect_web:// �ղ�

			Student stu = new Student(newsid, title, source, wap_thumb,
					create_time, nickname);

			if (!studentDao.isExist(newsid, "test1")) {// ������
				studentDao.insert(stu, "test1");
				Toast.makeText(this, "�ղسɹ�", Toast.LENGTH_SHORT).show();

			} else {// ����
				studentDao.delete(newsid, "test1");
				Toast.makeText(this, "ȡ���ղ�", Toast.LENGTH_SHORT).show();

			}
			break;
		case R.id.iv_share_web:// ����
			Toast.makeText(this, "����ɹ�", Toast.LENGTH_SHORT).show();

			break;

		default:
			break;
		}
	}

	/**
	 * ��¼�����ʷ
	 */
	public void history() {
		Student stu = new Student(newsid, title, source, wap_thumb,
				create_time, nickname);
		studentDao.insert(stu, "test2");

	}
}
