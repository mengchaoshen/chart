package com.chart.factory;

import org.apache.http.conn.ssl.SSLSocketFactory;

import com.lidroid.xutils.HttpUtils;

public class HttpFactory {

	private static HttpUtils instance;
	
	private final static Integer REQUEST_RETRY_COUNT = 1;
	
	private final static Integer TIME_OUT = 1000;
	
	private final static Integer HTTP_CACHE_SIZE = 0;
	
	private HttpFactory(){
		
	}
	
	public static HttpUtils getInstance(){
		if(null != instance){
			return instance;
		}
		instance = new HttpUtils(TIME_OUT);
		instance.configRequestRetryCount(REQUEST_RETRY_COUNT);
		instance.configSSLSocketFactory(SSLSocketFactory.getSocketFactory());
		instance.configHttpCacheSize(HTTP_CACHE_SIZE);
		return instance;
	}
}
