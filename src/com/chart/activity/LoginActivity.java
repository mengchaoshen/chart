package com.chart.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chart.BaseActivity;
import com.chart.R;
import com.chart.action.LoginAction;
import com.chart.constant.ActionConstant;
import com.chart.constant.GlobConstant;
import com.chart.constant.HttpState;
import com.chart.constant.Params;
import com.chart.interfaces.HttpCallback;
import com.chart.interfaces.OnDialogClickListener;
import com.chart.service.OnlineService;
import com.chart.widget.IsSureDialog;
import com.chart.widget.ProgressBarDialog;
import com.lidroid.xutils.http.RequestParams;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private EditText edtTxt_studyId;

	private EditText edtTxt_password;

	private Button btn_submit;

	private TextView txt_warn;
	
	private ProgressBarDialog progressBarDialog;
	
	private IsSureDialog isSureDialog;
	
	private LoginAction loginAction;

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

	/**
	 * 设置端口参数等
	 */
	private void setParam(){
		SharedPreferences account = this.getSharedPreferences(Params.DEFAULT_PRE_NAME,Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = account.edit();
		Log.e("SERVER_IP", GlobConstant.SERVER_IP);
		Log.e("SERVER_PORT", GlobConstant.SERVER_PORT);
		Log.e("PUSH_PORT", GlobConstant.PUSH_PORT);
		Log.e("getStudyId", baseApp.user.getStudyId());
		editor.putString(Params.SERVER_IP, GlobConstant.SERVER_IP);
		editor.putString(Params.SERVER_PORT, GlobConstant.SERVER_PORT);
		editor.putString(Params.PUSH_PORT, GlobConstant.PUSH_PORT);
		editor.putString(Params.USER_NAME, baseApp.user.getStudyId());
		editor.putString(Params.SENT_PKGS, "0");
		editor.putString(Params.RECEIVE_PKGS, "0");
		editor.commit();
	}
	
	private void startService(){
		Intent startSrv = new Intent(this, OnlineService.class);
		startSrv.putExtra("CMD", "RESET");
		this.startService(startSrv);
	}
	
	@Override
	public void onClick(View arg0) {
		if (null == edtTxt_studyId.getText().toString()
				|| "".equals(edtTxt_studyId.getText().toString())
				|| null == edtTxt_password.getText().toString()
				|| "".equals(edtTxt_password.getText().toString())) {
			txt_warn.setVisibility(View.VISIBLE);
			txt_warn.setText(getResources().getString(R.string._warn1));
			return;
		} else {
			txt_warn.setVisibility(View.GONE);
		}
		progressBarDialog = new ProgressBarDialog(LoginActivity.this);
		progressBarDialog.show();
		
		RequestParams loginParams = new RequestParams();
		loginParams.addQueryStringParameter("studyId", edtTxt_studyId.getText()
				.toString());
		loginParams.addQueryStringParameter("password", edtTxt_password
				.getText().toString());

		loginAction = new LoginAction(LoginActivity.this,
				ActionConstant.LOGIN, loginParams);
		loginAction.setHttpCallback(new HttpCallback() {

			@Override
			public void onCallback(int state, String result) {
				switch (state) {
				case HttpState.SUCCESS:
					progressBarDialog.dismiss();
					if (baseApp.user.isCorrect()) {
						Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
						//TODO 登录成功
						setParam();
						startService();
						startActivity(new Intent(LoginActivity.this, MainFunctionActivity.class));
						finish();
						overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
					} else {
						txt_warn.setVisibility(View.VISIBLE);
						txt_warn.setText(getResources()
								.getString(R.string._warn2));
					}
					break;
				case HttpState.FAILURE:
					progressBarDialog.dismiss();
					isSureDialog = new IsSureDialog(LoginActivity.this,
							getResources().getString(
									R.string.warn_network_error));
					isSureDialog
							.setOnDialogClickListener(new OnDialogClickListener() {
								@Override
								public void onDialogClick(int viewId, Object o) {
									switch (viewId) {
									case R.id.btn_cancel:
										isSureDialog.dismiss();
										break;
									case R.id.btn_sure:
										isSureDialog.dismiss();
										progressBarDialog.show();
										loginAction.sendGet();
										break;
									default:
										break;
									}
								}
							});
					isSureDialog.show();
					break;
				case HttpState.NETWORK_AVAILABLE:
					progressBarDialog.dismiss();
					Toast.makeText(
							LoginActivity.this,
							getResources().getString(
									R.string.warn_network_not_available),
							Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		});
		loginAction.sendGet();
	}
}
