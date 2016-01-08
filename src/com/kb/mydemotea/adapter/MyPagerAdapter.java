package com.kb.mydemotea.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kb.mydemotea.asynctask.LoadImageAsyncTask;

public class MyPagerAdapter extends PagerAdapter {
	private List<Map<String, Object>> list;
	private Context context;
	private List<ImageView> viewList;// -----------------

	public MyPagerAdapter(Context context) {
		super();
		this.context = context;
		list = new ArrayList<Map<String, Object>>();
		viewList = new ArrayList<ImageView>();
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public void addList(List<Map<String, Object>> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		ImageView iv = new ImageView(context);
		iv.setClickable(true);
		String imgUrl = list.get(position).get("image").toString();
		// Log.i("-------", imgUrl);
		String info = list.get(position).get("title").toString();
		new LoadImageAsyncTask(context, iv, null).execute(imgUrl);
		viewList.add(iv);
		container.addView(iv);
		return iv;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		// super.destroyItem(container, position, object);
		container.removeView(viewList.get(position));// ----------viewList中还存在之前的view？
	}
}
