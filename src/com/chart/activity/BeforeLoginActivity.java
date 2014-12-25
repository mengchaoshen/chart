package com.chart.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;

import com.chart.BaseActivity;
import com.chart.R;

public class BeforeLoginActivity extends BaseActivity {

//	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		findViewById();
		init();
	}
	
	@Override
	public void findViewById() {
//		iv = (ImageView) findViewById(R.id.iv);
	}

	@Override
	public void init() {
//		iv.setImageDrawable(getResources().getDrawable(R.drawable.before_login));
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				startActivity(new Intent(BeforeLoginActivity.this, LoginActivity.class));
				finish();
			}
		}, 2000);
		
	}

	@Override
	public void backToActivity() {

	}

}
