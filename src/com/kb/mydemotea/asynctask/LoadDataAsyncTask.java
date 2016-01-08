package com.kb.mydemotea.asynctask;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.kb.mydemotea.R;
import com.kb.mydemotea.adapter.MyAdapter;
import com.kb.mydemotea.pulltorefresh.PullToRefreshListView;
import com.kb.mydemotea.utils.HttpURLConnHelper;
import com.kb.mydemotea.utils.JsonUtils;

/**
 * 异步任务，请求列表数据
 * 
 * @author Administrator
 * 
 */
public class LoadDataAsyncTask extends AsyncTask<String, Void, byte[]> {

	private ListView listView;
	private Context context;
	private MyAdapter adapter;
	private ProgressDialog progressDialog;
	private static boolean flag = false;
	public static PullToRefreshListView pullToRefreshListView = null;

	public LoadDataAsyncTask(ListView listView, MyAdapter adapter,
			Context context) {
		super();
		this.listView = listView;
		this.context = context;
		this.adapter = adapter;
		progressDialog = new ProgressDialog(context);

		progressDialog.setIcon(R.drawable.logo);
		progressDialog.setMessage("加载中...");
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progressDialog.show();
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
				List<Map<String, Object>> list = JsonUtils
						.getListFromJson(new String(result, "utf-8"));
				// MyAdapter adapter = new MyAdapter(context);
				// listView.setAdapter(adapter);
				adapter.addList(list);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
		}

		// if (result != null) {
		// if (no == 0) {
		// try {
		// adapter.setList(JsonUtils.getListFromJson(new String(
		// result, "utf-8")));
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// if (flag) {
		// // 让“正在刷新”回去
		// pullToRefreshListView.onRefreshComplete();
		//
		// } else {
		// flag = true;
		// }
		// } else {
		// try {
		// adapter.addList(JsonUtils.getListFromJson(new String(
		// result, "utf-8")));
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }

		progressDialog.dismiss();
	}
}
