package com.chart.model;

public class User extends BaseJson {

	private static final long serialVersionUID = 4295109848313815330L;
	
	private boolean correct;

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
}
