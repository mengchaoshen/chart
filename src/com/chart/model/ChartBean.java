package com.chart.model;

import java.io.Serializable;
import java.util.List;

public class ChartBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1929592728485737243L;
	private List<ChartItem> chartItemList;

	public List<ChartItem> getChartItemList() {
		return chartItemList;
	}

	public void setChartItemList(List<ChartItem> chartItemList) {
		this.chartItemList = chartItemList;
	}
	
}
