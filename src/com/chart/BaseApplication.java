package com.chart;

import java.io.Serializable;

import android.app.Application;

import com.chart.model.User;

public class BaseApplication extends Application implements Serializable{

	private static final long serialVersionUID = 5805107981447373032L;

	public User user = new User();
	
}
