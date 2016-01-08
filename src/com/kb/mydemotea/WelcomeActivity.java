package com.kb.mydemotea;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class WelcomeActivity extends Activity {
	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private List<ImageView> list;
	private LinearLayout linearLayout;
	private Button button;

	private void initView() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) findViewById(R.id.viewPager_welcome);
		list = new ArrayList<ImageView>();
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout_welcome);
		button = (Button) findViewById(R.id.btn_welcome);
		button.setClickable(false);// ----------------

		ImageView imageView1 = new ImageView(this);
		imageView1.setImageResource(R.drawable.slide1);
		ImageView imageView2 = new ImageView(this);
		imageView2.setImageResource(R.drawable.slide2);
		ImageView imageView3 = new ImageView(this);
		imageView3.setImageResource(R.drawable.slide3);
		// 设置图片的填充方式
		imageView1.setScaleType(ScaleType.FIT_XY);
		imageView2.setScaleType(ScaleType.FIT_XY);
		imageView3.setScaleType(ScaleType.FIT_XY);

		list.add(imageView1);
		list.add(imageView2);
		list.add(imageView3);

		for (int i = 0; i < list.size(); i++) {
			linearLayout.getChildAt(i).setSelected(false);
		}
		linearLayout.getChildAt(0).setSelected(true);

	}

	private void initCtrl() {
		pagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 3;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(list.get(position));
				return list.get(position);// ---------------
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				// super.destroyItem(container, position, object);
				// container.removeViewAt(position);// -----------------
				container.removeView(list.get(position));// ---------含义
			}
		};

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

		int flag = sharedPreferences.getInt("welcome", 0);

		if (flag == 0) {
			Editor editor = sharedPreferences.edit();
			editor.putInt("welcome", 1);
			editor.commit();

			initView();
			initCtrl();
			viewPager.setAdapter(pagerAdapter);
			viewPager.setOnPageChangeListener(new OnPageChangeListener() {// ---------写在init()里？

						@Override
						public void onPageSelected(int arg0) {
							// TODO Auto-generated method stub
							// 触发小点变换
							for (int i = 0; i < list.size(); i++) {
								linearLayout.getChildAt(i).setSelected(false);
							}
							linearLayout.getChildAt(arg0).setSelected(true);
							if (arg0 < 2) {
								button.setClickable(false);
							} else {
								button.setClickable(true);
							}
						}

						@Override
						public void onPageScrolled(int arg0, float arg1,
								int arg2) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onPageScrollStateChanged(int arg0) {
							// TODO Auto-generated method stub

						}
					});
		} else {

			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	public void onClick(View view) {
		for (int i = 0; i < list.size(); i++) {
			linearLayout.getChildAt(i).setSelected(false);
		}
		view.setSelected(true);
		viewPager.setCurrentItem(linearLayout.indexOfChild(view));
	}

	public void onButtonClick(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
