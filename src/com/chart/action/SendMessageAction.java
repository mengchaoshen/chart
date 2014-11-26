package com.chart.action;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.chart.BaseAction;
import com.chart.BaseApplication;
import com.chart.model.BaseJson;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.util.LogUtils;

public class SendMessageAction extends BaseAction {
	
	private Context context;

	public SendMessageAction(Context context, String url, RequestParams params) {
		super(context, url, params);
		this.context = context;
	}

	@Override
	public void paramParse(String jsonResult) {

		LogUtils.e(jsonResult);
		BaseJson baseJson = JSON.parseObject(jsonResult, BaseJson.class);
		if(null != baseJson){
			((BaseApplication) context.getApplicationContext()).baseJson = baseJson;
		}
	}

}
