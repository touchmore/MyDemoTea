package com.kb.mydemotea.fragment;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kb.mydemotea.R;
import com.kb.mydemotea.WebActivity;
import com.kb.mydemotea.adapter.MyAdapter;
import com.kb.mydemotea.asynctask.LoadDataAsyncTask;
import com.kb.mydemotea.asynctask.LoadPicAsyncTask;
import com.kb.mydemotea.config.Config;
import com.kb.mydemotea.pulltorefresh.PullToRefreshListView;

public class Fragment_tops extends Fragment {
	private ViewPager viewPager;
	private View view;
	private LinearLayout linearLayout;
	private ListView listView;
	private PullToRefreshListView pullToRefreshListView;

	private MyAdapter adapter;

	private int no;
	// 幻灯片

	// 幻灯片信息
	private TextView textView;

	private void initView() {
		viewPager = (ViewPager) view.findViewById(R.id.vp_tops);
		linearLayout = (LinearLayout) view.findViewById(R.id.ll_tops);
		// 幻灯片信息
		textView = (TextView) view.findViewById(R.id.tv_tops);

		// 信息列表
		pullToRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.lv_tops);
		listView = pullToRefreshListView.getRefreshableView();
		for (int i = 0; i < 3; i++) {
			linearLayout.getChildAt(i).setSelected(false);
		}
		linearLayout.getChildAt(0).setSelected(true);
	}

	private void initCtrl() {
		// pagerAdapter = new MyPagerAdapter();
		adapter = new MyAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_tops, null);
		initView();
		initCtrl();
		listView.setAdapter(adapter);
		// 开启异步任务为幻灯片viewpager请求数据
		new LoadPicAsyncTask(textView, viewPager, linearLayout, getActivity())
				.execute(Config.HOMEPATH);

		// 为ListView开启异步任务请求数据
		new LoadDataAsyncTask(listView, adapter, getActivity())
				.execute(com.kb.mydemotea.config.Config.HEADLINEPATH);
		// 为ListView设置监听
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), WebActivity.class);
				Map<String, Object> map = (Map<String, Object>) parent
						.getItemAtPosition(position);
				String myid = map.get("id").toString();
				String title = map.get("title").toString();
				String source = map.get("source").toString();
				String description = map.get("description").toString();
				String wap_thumb = map.get("wap_thumb").toString();
				String create_time = map.get("create_time").toString();
				String nickname = map.get("nickname").toString();

				intent.putExtra("myid", myid);
				intent.putExtra("title", title);
				intent.putExtra("source", source);
				intent.putExtra("wap_thumb", wap_thumb);
				intent.putExtra("create_time", create_time);
				intent.putExtra("nickname", nickname);
				Log.i("tag2---->", myid + "--" + title + "--" + source + "--"
						+ description + "--" + wap_thumb + "--" + create_time
						+ "--" + nickname);
				startActivity(intent);
			}
		});

		// // 设置刷新监听
		// pullToRefreshListView.setOnRefreshListener(new OnRefreshListener() {
		//
		// @Override
		// public void onRefresh() {
		// // TODO Auto-generated method stub
		// no = 0;
		// }
		// });
		// // 设置加载监听
		// pullToRefreshListView
		// .setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
		//
		// @Override
		// public void onLastItemVisible() {
		// // TODO Auto-generated method stub
		// no++;
		// }
		// });
		return view;
	}
}
