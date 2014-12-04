package com.chart.action;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.chart.BaseAction;
import com.chart.BaseApplication;
import com.chart.model.AnnJson;
import com.lidroid.xutils.http.RequestParams;

public class AnnAction extends BaseAction {

	private Context context;
	
	public AnnAction(Context context, String url, RequestParams params) {
		super(context, url, params);
		this.context = context;
	}

	@Override
	public void paramParse(String jsonResult) {
		AnnJson annJson = JSON.parseObject(jsonResult, AnnJson.class);
		((BaseApplication) context.getApplicationContext()).annList = annJson.getAnnList();
	}

}
