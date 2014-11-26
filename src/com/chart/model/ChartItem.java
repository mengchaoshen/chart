package com.chart.model;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

@Table(name="chart")
public class ChartItem implements Serializable{

	private static final long serialVersionUID = 7124422452022675979L;
	
	@Id
	private int id;
	
	@Column(column="studyId")
	private String studyId;
	
	@Column(column="head")
	private String head;
	
	@Column(column="chartText")
	private String chartText;

	@Column(column="isOther")
	private boolean isOther;

	@Column(column="chartType")
	private String chartType;
	
	@Column(column="chartObject")
	private String chartObject;
	
	public ChartItem() {
		super();
	}

	public ChartItem(String studyId, String head, String chartText,
			boolean isOther, String chartType, String chartObject) {
		super();
		this.studyId = studyId;
		this.head = head;
		this.chartText = chartText;
		this.isOther = isOther;
		this.chartType = chartType;
		this.chartObject = chartObject;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getChartText() {
		return chartText;
	}

	public void setChartText(String chartText) {
		this.chartText = chartText;
	}

	public boolean isOther() {
		return isOther;
	}

	public void setOther(boolean isOther) {
		this.isOther = isOther;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChartObject() {
		return chartObject;
	}

	public void setChartObject(String chartObject) {
		this.chartObject = chartObject;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	
}
