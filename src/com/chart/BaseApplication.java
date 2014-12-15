package com.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.chart.model.Announcement;
import com.chart.model.BaseJson;
import com.chart.model.User;

public class BaseApplication extends Application implements Serializable{

	private static final long serialVersionUID = 5805107981447373032L;

	public User user = new User();
	
	public BaseJson baseJson = new BaseJson();
	
	public String chartTitle = null;
	
	public String chartObject = null;
	
	public String chartType = null;
	
	public List<User> userList = new ArrayList<User>();
	
	public boolean uploadSuccess;
	
	public List<Announcement> annList = new ArrayList<Announcement>();
	
	public int url;
	
	public String spTxt;
}
