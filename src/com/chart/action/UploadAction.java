package com.chart.action;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.chart.BaseAction;
import com.chart.BaseApplication;
import com.chart.model.BaseJson;
import com.lidroid.xutils.http.RequestParams;

public class UploadAction extends BaseAction {

	private BaseApplication baseApp;
	
	public UploadAction(Context context, String url, RequestParams params) {
		super(context, url, params);
		baseApp = (BaseApplication) context.getApplicationContext();
	}

	@Override
	public void paramParse(String jsonResult) {

		BaseJson baseJson = JSON.parseObject(jsonResult, BaseJson.class);
		if(null != baseJson && baseJson.isSuccess()){
			baseApp.uploadSuccess = true;
		}else{
			baseApp.uploadSuccess = false;
		}
	}

}
