package com.chart.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chart.BaseActivity;
import com.chart.R;

public class LoginActivity extends BaseActivity implements OnClickListener{

	private EditText edtTxt_studyId;
	
	private EditText edtTxt_password;
	
	private Button btn_submit;
	
	private TextView txt_warn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findViewById();
		init();
	}
	
	@Override
	public void findViewById() {
		edtTxt_studyId = (EditText) findViewById(R.id.edtTxt_studyId);
		edtTxt_password = (EditText) findViewById(R.id.edtTxt_password);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		txt_warn = (TextView) findViewById(R.id.txt_warn);
	}

	@Override
	public void init() {
		btn_submit.setOnClickListener(LoginActivity.this);
	}

	@Override
	public void backToActivity() {
		
	}

	@Override
	public void onClick(View arg0) {
		if(null == edtTxt_studyId.getText().toString() || "".equals(edtTxt_studyId.getText().toString()) || null == edtTxt_password.getText().toString() || "".equals(edtTxt_password.getText().toString())){
			txt_warn.setVisibility(View.VISIBLE);
			txt_warn.setText(getResources().getString(R.string._warn1));
		}else{
			txt_warn.setVisibility(View.GONE);
		}
	}

}
