package com.chart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.chart.BaseActivity;
import com.chart.R;
import com.chart.action.AnnAction;
import com.chart.adapter.AnnAdapter;
import com.chart.constant.ActionConstant;
import com.chart.constant.HttpState;
import com.chart.interfaces.HttpCallback;
import com.chart.interfaces.OnDialogClickListener;
import com.chart.widget.IsSureDialog;
import com.chart.widget.ProgressBarDialog;
import com.lidroid.xutils.http.RequestParams;

public class AnnActivity extends BaseActivity implements OnClickListener{

	private Button btn_back;
	
	private ListView ltv_ann;
	
	private Button btn_upload;
	
	private ProgressBarDialog progressBarDialog;
	
	private IsSureDialog isSureDialog;
	
	private AnnAction annAction;
	
//	private List<Announcement> annList;
	
	private AnnAdapter annAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ann);
		findViewById();
		init();
	}
	
	
	@Override
	public void findViewById() {
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_upload = (Button) findViewById(R.id.btn_upload);
		ltv_ann = (ListView) findViewById(R.id.ltv_ann);
	}

	@Override
	public void init() {
		progressBarDialog = new ProgressBarDialog(AnnActivity.this);
		btn_back.setOnClickListener(this);
		btn_upload.setOnClickListener(this);
		getAnn();
	}
	
	private void getAnn(){
		progressBarDialog.show();
		RequestParams annParams = new RequestParams();
		annAction = new AnnAction(AnnActivity.this, ActionConstant.DOWNLOAD_AN, annParams);
		annAction.setHttpCallback(new HttpCallback() {
			
			@Override
			public void onCallback(int state, String result) {
				switch (state) {
				case HttpState.SUCCESS:
					progressBarDialog.dismiss();
					annAdapter = new AnnAdapter(AnnActivity.this, baseApp.annList);
					ltv_ann.setAdapter(annAdapter);
					break;
				case HttpState.FAILURE:
					progressBarDialog.dismiss();
					isSureDialog = new IsSureDialog(AnnActivity.this,
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
										annAction.sendPost();
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
							AnnActivity.this,
							getResources().getString(
									R.string.warn_network_not_available),
							Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		});
		annAction.sendPost();
	}

	@Override
	public void backToActivity() {
		startActivity(new Intent(AnnActivity.this, MainFunctionActivity.class));
		finish();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			backToActivity();
			break;
		case R.id.btn_upload:
			startActivity(new Intent(AnnActivity.this, UploadActivity.class));
			finish();
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			backToActivity();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
