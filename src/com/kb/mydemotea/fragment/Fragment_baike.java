package com.kb.mydemotea.fragment;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.kb.mydemotea.R;
import com.kb.mydemotea.WebActivity;
import com.kb.mydemotea.adapter.MyAdapter;
import com.kb.mydemotea.asynctask.LoadDataAsyncTask;
import com.kb.mydemotea.config.Config;

/**
 * 百科
 * 
 * @author Administrator
 * 
 */
public class Fragment_baike extends Fragment {
	private boolean isBottom;// 是否到底部
	private int currentIndex = 1;// 当前页
	private int pageno;
	// private ListView listView;
	private MyAdapter adapter;
	private View footer;
	private View view;

	private ListView listView = null;

	// private PullToRefreshListView pullToRefreshListView = null;
	// private static int no;

	// private void initView() {
	// pullToRefreshListView = (PullToRefreshListView) view
	// .findViewById(R.id.lv_baike);
	// listView = pullToRefreshListView.getRefreshableView();
	// }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		listView = new ListView(getActivity());// 所在方法，返回一个View
		Bundle bundle = getArguments();
		pageno = bundle.getInt("pageno");// 顶部滚动条的no
		adapter = new MyAdapter(getActivity());
		// 向listview中添加底部(要写在setAdapter执行)
		footer = View.inflate(getActivity(), R.layout.loader, null);
		// footer.setFocusable(false);
		// footer.setClickable(false);
		listView.addFooterView(footer);

		listView.setAdapter(adapter);

		switch (pageno) {
		case 1:// 百科
			new LoadDataAsyncTask(listView, adapter, getActivity())
					.execute(Config.BAIKEPATH + "&rows=15&page=0&type=16");
			break;
		case 2:// 资讯
			new LoadDataAsyncTask(listView, adapter, getActivity())
					.execute(Config.INFORMATIONPATH + "&rows=15&page=0&type=52");

			break;
		case 3:// 经营
			new LoadDataAsyncTask(listView, adapter, getActivity())
					.execute(Config.OPERATE + "&rows=15&page=0&type=53");

			break;
		case 4:// 数据
			new LoadDataAsyncTask(listView, adapter, getActivity())
					.execute(Config.DATAPAGER + "&rows=15&page=0&type=54");

			break;

		default:
			break;
		}

		/**
		 * 为ListView设置条目点击监听
		 */
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
					switch (pageno) {
					case 1:// 百科
						new LoadDataAsyncTask(listView, adapter, getActivity())
								.execute(Config.BAIKEPATH + "&rows=15&page="
										+ currentIndex + "&type=16");
						break;
					case 2:// 资讯
						new LoadDataAsyncTask(listView, adapter, getActivity())
								.execute(Config.INFORMATIONPATH
										+ "&rows=15&page=" + currentIndex
										+ "&type=52");
						break;
					case 3:// 经营
						new LoadDataAsyncTask(listView, adapter, getActivity())
								.execute(Config.OPERATE + "&rows=15&page="
										+ currentIndex + "&type=53");

						break;
					case 4:// 数据
						new LoadDataAsyncTask(listView, adapter, getActivity())
								.execute(Config.DATAPAGER + "&rows=15&page="
										+ currentIndex + "&type=54");
						break;

					default:
						break;
					}

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

		return listView;
	}
}
