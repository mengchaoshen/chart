package com.chart.model;

import java.io.Serializable;

public class MainFunction implements Serializable{

	private static final long serialVersionUID = 8496148575139383013L;
	
	private Integer id;
	
	private String name;
	
	private int iconId;

	public MainFunction() {
		super();
	}

	public MainFunction(Integer id, String name, int iconId) {
		super();
		this.id = id;
		this.name = name;
		this.iconId = iconId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

}
