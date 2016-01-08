package com.kb.mydemotea.asynctask;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.kb.mydemotea.utils.HttpURLConnHelper;
import com.kb.mydemotea.utils.JsonUtils;

/**
 * 异步任务，加载web
 * 
 * @author Administrator
 * 
 */
public class LoadWebAsyncTask extends AsyncTask<String, Void, byte[]> {
	private Context context;
	private Map<String, Object> map;
	private TextView title_web;
	private TextView source_web;
	private TextView create_time_web;
	private WebView webView;

	public LoadWebAsyncTask(Context context, TextView title_web,
			TextView source_web, TextView create_time_web, WebView webView) {
		super();
		this.context = context;
		this.title_web = title_web;
		this.source_web = source_web;
		this.create_time_web = create_time_web;
		this.webView = webView;

	}

	@Override
	protected byte[] doInBackground(String... params) {
		// TODO Auto-generated method stub
		return HttpURLConnHelper.loadByteFromURL(params[0]);
	}

	@Override
	protected void onPostExecute(byte[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		if (result != null) {
			try {
				map = JsonUtils.getMapFromWebJson(new String(result, "utf-8"));
				String title = map.get("title").toString();
				String source = map.get("source").toString();
				String create_time = map.get("create_time").toString();
				String weiboUrl = map.get("weiboUrl").toString();
				Log.i("web----", title + "--" + source + "--" + create_time
						+ "weiboUrl");
				title_web.setText(title);
				source_web.setText(source);
				create_time_web.setText(create_time);
				webView.loadUrl(weiboUrl);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Log.i("web----", "请求失败");

		}
	}
}
