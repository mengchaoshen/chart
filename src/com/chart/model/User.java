package com.chart.model;

public class User extends BaseJson {

	private static final long serialVersionUID = 4295109848313815330L;
	
	private boolean correct;
	
	private String studyId;
	
	private String userName;

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
