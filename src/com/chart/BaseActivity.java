package com.chart;

import android.app.Activity;
import android.os.Bundle;

/**
 * @ClassName: BaseActivity
 * @Description: 基础Activity
 * @Company:税友软件集团股份有限公司
 * @Author mengchaoshen
 * @Version 1.0
 * @Date 2014-10-28 上午11:02:43
 */
public abstract class BaseActivity extends Activity {

	public BaseApplication baseApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseApp = (BaseApplication) getApplicationContext();
	}

	public abstract void findViewById();
	
	public abstract void init();

	public abstract void backToActivity();

}
