package com.kb.mydemotea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kb.mydemotea.fragment.Fragment_baike;
import com.kb.mydemotea.fragment.Fragment_tops;

public class MainActivity extends FragmentActivity {
	private HorizontalScrollView horizontalScrollView;
	private LinearLayout linearLayout;
	private ViewPager viewPager;
	private FragmentPagerAdapter fragmentPagerAdapter;
	private DrawerLayout drawerLayout;// ����
	private LinearLayout right;// �໬
	private EditText et_search;// ������
	private TextView tv_tea;// ��������

	private void initView() {
		horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hs_main);
		linearLayout = (LinearLayout) findViewById(R.id.ll_main);
		viewPager = (ViewPager) findViewById(R.id.vp_main);
		et_search = (EditText) findViewById(R.id.et_search);
		tv_tea = (TextView) findViewById(R.id.tv_tea);

		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			TextView textView = (TextView) linearLayout.getChildAt(i);
			textView.setTextColor(0xff619D01);
		}
		TextView textView = (TextView) linearLayout.getChildAt(0);
		textView.setTextColor(0xffff0000);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
		right = (LinearLayout) findViewById(R.id.right);
		// ��ȡ��right�Ĳ���
		LayoutParams layoutParams = right.getLayoutParams();
		layoutParams.width = getResources().getDisplayMetrics().widthPixels;
		right.setLayoutParams(layoutParams);
	}

	private void initCtrl() {
		fragmentPagerAdapter = new FragmentPagerAdapter(
				getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return linearLayout.getChildCount();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				Fragment fragment = null;
				switch (arg0) {
				case 0:// ͷ��
					fragment = new Fragment_tops();
					break;
				case 1:// �ٿ�
					fragment = new Fragment_baike();
					break;
				case 2:// ��Ѷ
					fragment = new Fragment_baike();
					break;
				case 3:// ��Ӫ
					fragment = new Fragment_baike();
					break;
				case 4:// ����
					fragment = new Fragment_baike();
					break;

				default:
					fragment = new Fragment();
					break;
				}
				Bundle bundle = new Bundle();
				bundle.putInt("pageno", arg0);
				fragment.setArguments(bundle);

				return fragment;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				return super.instantiateItem(container, position);
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				// super.destroyItem(container, position, object);
			}
		};
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initCtrl();

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				horizontalScrollView.smoothScrollTo(60 * arg0, 0);
				// ������Ӧ��textview
				for (int i = 0; i < linearLayout.getChildCount(); i++) {
					TextView textView = (TextView) linearLayout.getChildAt(i);
					textView.setTextColor(0xff666666);
				}
				TextView textView = (TextView) linearLayout.getChildAt(arg0);
				textView.setTextColor(0xff619D01);

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
		viewPager.setAdapter(fragmentPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// ��������Ŀ���
	public void onClick(View view) {
		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			TextView textView = (TextView) linearLayout.getChildAt(i);
			textView.setTextColor(0xff666666);
		}
		TextView textView = (TextView) view;
		textView.setTextColor(0xff619D01);

		// ������Ӧ��viewpager
		viewPager.setCurrentItem(linearLayout.indexOfChild(view));
	}

	// ����
	public void onMoreClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.iv_more:
			// �����������Ǹ�view
			drawerLayout.openDrawer(right);
			break;
		case R.id.iv_back:
			// �رղ������Ǹ�view
			drawerLayout.closeDrawer(right);
			break;

		case R.id.tv_shoucang:// �ղؼ�
			intent = new Intent(getApplicationContext(), CollectActivity.class);
			intent.putExtra("tableName", "test1");
			startActivity(intent);
			break;
		case R.id.tv_history:// ��ʷ��¼
			intent = new Intent(getApplicationContext(), CollectActivity.class);
			intent.putExtra("tableName", "test2");
			startActivity(intent);

			break;
		case R.id.tv_banquan:// ��Ȩ��Ϣ
			intent = new Intent(getApplicationContext(), BanquanActivity.class);
			startActivity(intent);
			break;
		case R.id.tv_yijian:// ���
			intent = new Intent(getApplicationContext(),
					SuggestionActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

	/**
	 * ����
	 * 
	 * @param view
	 */
	public void onSearch(View view) {
		Intent intent = new Intent(getApplicationContext(),
				SearchActivity.class);
		intent.putExtra("keyWord", et_search.getText().toString());
		startActivity(intent);

	}

	/**
	 * ����
	 */
	public void onRemenClick(View view) {
		Intent intent = new Intent(getApplicationContext(),
				SearchActivity.class);
		intent.putExtra("keyWord", tv_tea.getText().toString());
		startActivity(intent);
	}
}
