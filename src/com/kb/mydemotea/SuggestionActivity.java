package com.kb.mydemotea;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class SuggestionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggestion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.suggestion, menu);
		return true;
	}

	public void onSuggestionClick(View view) {
		finish();
	}

	public void submit(View view) {
		Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT)
				.show();
	}
}
