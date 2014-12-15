package com.chart.model;

import java.io.Serializable;

public class Face implements Serializable{

	private static final long serialVersionUID = -3900106241472448605L;
	
	private int id;
	
	private int url;
	
	private String spTxt;

	public Face() {
		super();
	}


	public Face(int id, int url, String spTxt) {
		super();
		this.id = id;
		this.url = url;
		this.spTxt = spTxt;
	}


	public String getSpTxt() {
		return spTxt;
	}


	public void setSpTxt(String spTxt) {
		this.spTxt = spTxt;
	}


	public Face(int id, int url) {
		super();
		this.id = id;
		this.url = url;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUrl() {
		return url;
	}

	public void setUrl(int url) {
		this.url = url;
	}


}
