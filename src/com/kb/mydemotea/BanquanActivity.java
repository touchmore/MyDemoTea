package com.kb.mydemotea;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class BanquanActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banquan);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.banquan, menu);
		return true;
	}

	public void onBanquanClick(View view) {
		finish();
	}
}
