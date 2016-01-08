package com.kb.mydemotea.asynctask;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kb.mydemotea.adapter.MyPagerAdapter;
import com.kb.mydemotea.utils.HttpURLConnHelper;
import com.kb.mydemotea.utils.JsonUtils;

/**
 * 异步任务，请求幻灯片数据
 * 
 * @author Administrator
 * 
 */
public class LoadPicAsyncTask extends AsyncTask<String, Void, byte[]> {
	private ViewPager viewPager;
	private Context context;
	private List<Map<String, Object>> data;
	private TextView textView;
	private LinearLayout linearLayout;

	// private List<ImageView> list;

	public LoadPicAsyncTask(TextView textView, ViewPager viewPager,
			LinearLayout linearLayout, Context context) {
		super();
		this.textView = textView;
		this.viewPager = viewPager;
		this.linearLayout = linearLayout;
		this.context = context;
		// list = new ArrayList<Map<String, Object>>();
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
				data = JsonUtils.getListFromJson2(new String(result, "utf-8"));
				MyPagerAdapter myPagerAdapter = new MyPagerAdapter(context);
				viewPager.setAdapter(myPagerAdapter);
				myPagerAdapter.setList(data);// -------------

				// 为viewpager设置监听，变动时更改图片信息
				viewPager.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						// TODO Auto-generated method stub
						// 变换该图片的信息
						String info = data.get(arg0).get("name").toString();
						textView.setText(info);
						// 变换小点
						for (int i = 0; i < 3; i++) {
							linearLayout.getChildAt(i).setSelected(false);
						}
						linearLayout.getChildAt(arg0).setSelected(true);

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// Toast.makeText(context, "网络异常,幻灯片加载失败",
			// Toast.LENGTH_SHORT).show();
		}
	}
}
