package com.chart;

import java.io.File;

import android.content.Context;

import com.chart.constant.HttpState;
import com.chart.factory.HttpFactory;
import com.chart.interfaces.HttpCallback;
import com.chart.util.NetManagerUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

public abstract class BaseAction {

	private Context context;

	private String url;

	private String target;

	private RequestParams params;

	private HttpCallback httpCallback;

	private HttpHandler<File> handlerFile;
	
	private HttpHandler<String> handlerString;

	// private final int TIMEOUT = 4000;
	//
	// private int timeout;

	public void setHttpCallback(HttpCallback httpCallback) {
		this.httpCallback = httpCallback;
	}

	public BaseAction(Context context, String url, RequestParams params) {
		this.context = context;
		this.url = url;
		this.params = params;
	}

	public BaseAction(Context context, String url, String target) {
		this.context = context;
		this.url = url;
		this.target = target;
	}

	/**
	 * 通信成功后再各自Action里面调用的方法
	 * 
	 * @param jsonResult
	 *            通信后返回的json串
	 */
	public abstract void paramParse(String jsonResult);

	/**
	 * GET方法，用来从后台获取信息
	 */
	public void sendGet() {
		if (!NetManagerUtil.isNetworkAvailable(context)) {
			if (null != httpCallback) {
				httpCallback.onCallback(HttpState.NETWORK_AVAILABLE, "");
			}
		} else {
			handlerString = HttpFactory.getInstance().send(HttpMethod.GET, url, params,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							if (null != responseInfo) {
								paramParse(responseInfo.result);
							}
							if (null != httpCallback) {
								httpCallback.onCallback(HttpState.SUCCESS, "");
							}
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							LogUtils.e("HTTPGet连接错误，错误信息是：" + msg);
							if (null != httpCallback) {
								httpCallback.onCallback(HttpState.FAILURE, msg);
							}
						}
					});
		}
	}

	/**
	 * POST方法，用来向后台提交数据
	 */
	public void sendPost() {
		handlerString = HttpFactory.getInstance().send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						if (null != responseInfo) {
							paramParse(responseInfo.result);
						}
						if (null != httpCallback) {
							httpCallback.onCallback(HttpState.SUCCESS, "");
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						LogUtils.e("HTTP POST连接错误，错误信息是：" + msg);
						if (null != httpCallback) {
							httpCallback.onCallback(HttpState.FAILURE, msg);
						}
					}

				});
	}

	/**
	 * 下载功能
	 */
	public void download() {
		handlerFile = HttpFactory.getInstance().download(url, target,
				new RequestCallBack<File>() {

					@Override
					public void onSuccess(ResponseInfo<File> responseInfo) {
						if (null != responseInfo) {
							paramParse(responseInfo.result.getPath());
						}
						if (null != httpCallback) {
							httpCallback.onCallback(HttpState.SUCCESS, "");
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						LogUtils.e("HTTP 下载错误，错误信息是：" + msg);
						if (null != httpCallback) {
							httpCallback.onCallback(HttpState.FAILURE, msg);
						}
					}

				});
	}

	/**
	 * 取消下载
	 */
	public void cancelDownload() {
		if (null != handlerFile) {
			handlerFile.cancel();
		}
	}
	
	/**
	 * 取消HTTP的连接，包括GET和POST方法
	 */
	public void cancelHttp(){
		if(null != handlerString){
			handlerString.cancel();
		}
	}
}
