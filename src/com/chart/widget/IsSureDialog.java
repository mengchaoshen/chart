package com.chart.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chart.R;
import com.chart.interfaces.OnDialogClickListener;

public class IsSureDialog extends Dialog implements android.view.View.OnClickListener{
	
	private String title;
	
	private TextView txt_title;
	
	private Button btn_sure;
	
	private Button btn_cancel;
	
	private OnDialogClickListener onDialogClickListener;
	
	public IsSureDialog(Context context, String title) {
		super(context, R.style.MyDialog);
		this.title = title;
		setContentView(R.layout.dialog_is_sure);
		findViewById();
	}
	
	public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener){
		this.onDialogClickListener = onDialogClickListener;
	}
	
	private void findViewById(){
		btn_sure = (Button) findViewById(R.id.btn_sure);
		btn_sure.setOnClickListener(this);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(this);
		txt_title = (TextView) findViewById(R.id.txt_title);
		txt_title.setText(title);
	}

	@Override
	public void onClick(View v) {
		onDialogClickListener.onDialogClick(v.getId(), null);
	}
	

}
