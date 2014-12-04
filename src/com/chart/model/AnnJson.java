package com.chart.model;

import java.io.Serializable;
import java.util.List;

public class AnnJson implements Serializable {

	private static final long serialVersionUID = -6483674944825506228L;

	private List<Announcement> annList;

	public List<Announcement> getAnnList() {
		return annList;
	}

	public void setAnnList(List<Announcement> annList) {
		this.annList = annList;
	}

}
