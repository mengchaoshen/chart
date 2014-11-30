package com.chart.action;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.chart.BaseAction;
import com.chart.BaseApplication;
import com.chart.model.MyFriendsJson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.util.LogUtils;

public class MyFriendsAction extends BaseAction {

	private Context context;
	
	public MyFriendsAction(Context context, String url, RequestParams params) {
		super(context, url, params);
		this.context = context;
	}

	@Override
	public void paramParse(String jsonResult) {
		LogUtils.e(jsonResult);
		MyFriendsJson myFriendsJson = JSON.parseObject(jsonResult, MyFriendsJson.class);
		if(null != myFriendsJson && myFriendsJson.isSuccess()){
			LogUtils.e(((BaseApplication) context.getApplicationContext()).user.getStudyId());
			((BaseApplication) context.getApplicationContext()).userList = myFriendsJson.getUserList();
		}
		
	}

}
