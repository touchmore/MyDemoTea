package com.kb.mydemotea.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.mydemotea.R;
import com.kb.mydemotea.R.drawable;
import com.kb.mydemotea.R.id;
import com.kb.mydemotea.R.layout;
import com.kb.mydemotea.asynctask.LoadImageAsyncTask;
import com.kb.mydemotea.utils.MD5Util;
import com.kb.mydemotea.utils.SDCardHelper;

/**
 * 自定义适配器，为ListView填充数据
 * 
 * @author Administrator
 * 
 */
public class MyAdapter extends BaseAdapter {
	private List<Map<String, Object>> list;
	private Context context;

	public MyAdapter(Context context) {
		super();
		this.context = context;
		list = new ArrayList<Map<String, Object>>();
	}

	// 设置数据集
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	// 添加数据集
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
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item, null);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.title_item);
			// holder.description = (TextView) convertView
			// .findViewById(R.id.description_item);
			holder.source = (TextView) convertView
					.findViewById(R.id.source_item);
			holder.create_time = (TextView) convertView
					.findViewById(R.id.create_time_item);
			holder.nickname = (TextView) convertView
					.findViewById(R.id.nickname_item);
			holder.wap_thumb = (ImageView) convertView
					.findViewById(R.id.imageView_item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String title = list.get(position).get("title").toString();
		String source = list.get(position).get("source").toString();
		String description = list.get(position).get("description").toString();
		String wap_thumb = list.get(position).get("wap_thumb").toString();
		String create_time = list.get(position).get("create_time").toString();
		String nickname = list.get(position).get("nickname").toString();

		holder.title.setText(title);
		holder.source.setText(source);
		// holder.description.setText(description);
		holder.create_time.setText(create_time);
		holder.nickname.setText(nickname);
		holder.wap_thumb.setImageResource(R.drawable.ic_launcher);
		// 开启异步任务，加载图片
		String name = MD5Util.getMD5(wap_thumb);
		File file = new File(
				SDCardHelper
						.getSDCardPublicDir(Environment.DIRECTORY_DOWNLOADS)
						+ File.separator + name);
		// 图片存储路径
		String path = SDCardHelper
				.getSDCardPublicDir(Environment.DIRECTORY_DOWNLOADS)
				+ File.separator + name;
		// 判断图片是否存在
		if (!file.exists()) {
			// 不存在
			Log.i("MyAdapter----", "图片不存在");
			new LoadImageAsyncTask(context, holder.wap_thumb, name)
					.execute(wap_thumb);
		} else {
			// 存在
			Log.i("MyAdapter----", "图片存在");
			byte[] b = SDCardHelper.loadFileFromSDCard(path);
			Bitmap bm = BitmapFactory.decodeByteArray(b, 0, b.length);
			holder.wap_thumb.setImageBitmap(bm);

		}

		return convertView;
	}

	class ViewHolder {
		TextView title;
		TextView description;
		TextView source;
		TextView create_time;
		TextView nickname;
		ImageView wap_thumb;
	}
}
