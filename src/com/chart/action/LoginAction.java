package com.chart.action;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.chart.BaseAction;
import com.chart.BaseApplication;
import com.chart.R;
import com.chart.constant.GlobConstant;
import com.chart.model.User;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.util.LogUtils;

public class LoginAction extends BaseAction {

	private Context context;
	
	public LoginAction(Context context, String url, RequestParams params) {
		super(context, url, params);
		this.context = context;
	}

	@Override
	public void paramParse(String jsonResult) {
		LogUtils.e(jsonResult);
		User user = JSON.parseObject(jsonResult, User.class);
		if(null != user && user.isSuccess()){
			((BaseApplication) context.getApplicationContext()).user = user;
			((BaseApplication) context.getApplicationContext()).chartTitle = context.getResources().getString(R.string.group_chart);
			((BaseApplication) context.getApplicationContext()).chartObject = context.getResources().getString(R.string.group_1);
			((BaseApplication) context.getApplicationContext()).chartType = GlobConstant.GROPU;
			LogUtils.e(((BaseApplication) context.getApplicationContext()).user.getStudyId());
		}
		
	}

}
