package com.chart.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.chart.R;

public class ProgressBarDialog extends Dialog {

	private TextView txt_progressBar;
	
	private String title;
	
	public ProgressBarDialog(Context context, String title) {
		super(context, R.style.progressBarDialog);
		setContentView(R.layout.dialog_progress_bar);
		this.title = title;
		findViewById();
		init();
	}
	
	public ProgressBarDialog(Context context) {
		super(context, R.style.progressBarDialog);
		setContentView(R.layout.dialog_progress_bar);
		findViewById();
		init();
	}
	
	private void findViewById(){
		setCanceledOnTouchOutside(false);//本Dialog点击边框不会消失，但是回退键有效
		txt_progressBar = (TextView) findViewById(R.id.txt_progressBar);
	}
	
	private void init(){
		if(null != title){
			txt_progressBar.setText(title);
		}
	}

}
