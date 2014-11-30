package com.chart.model;

import java.util.List;

public class MyFriendsJson extends BaseJson {

	private static final long serialVersionUID = -3229324505033362275L;

	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
