package com.chart.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.chart.BaseActivity;
import com.chart.R;
import com.chart.action.MyFriendsAction;
import com.chart.adapter.MyFriendsAdapter;
import com.chart.constant.ActionConstant;
import com.chart.constant.HttpState;
import com.chart.interfaces.HttpCallback;
import com.chart.interfaces.OnDialogClickListener;
import com.chart.model.User;
import com.chart.widget.IsSureDialog;
import com.chart.widget.ProgressBarDialog;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.util.LogUtils;

public class MyFriendsActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {

	private Button btn_back;

	private ListView list_myFriends;

	private MyFriendsAdapter myFriendsAdapter;
	
	private ProgressBarDialog progressBarDialog;
	
	private IsSureDialog isSureDialog;
	
	private List<User> userList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_friends);
		findViewById();
		init();
	}

	@Override
	public void findViewById() {
		btn_back = (Button) findViewById(R.id.btn_back);
		list_myFriends = (ListView) findViewById(R.id.list_myFriends);
	}

	@Override
	public void init() {
		btn_back.setOnClickListener(this);
		list_myFriends.setOnItemClickListener(this);
		getMyFriends();
	}

	private void getMyFriends() {
		progressBarDialog = new ProgressBarDialog(MyFriendsActivity.this);
		
		RequestParams params = new RequestParams();
		LogUtils.e("studyId:"+baseApp.user.getStudyId());
		params.addQueryStringParameter("studyId", baseApp.user.getStudyId());
		final MyFriendsAction myFriendsAction = new MyFriendsAction(
				MyFriendsActivity.this, ActionConstant.MY_FRIENDS, params);
		myFriendsAction.setHttpCallback(new HttpCallback() {

			@Override
			public void onCallback(int state, String result) {
				switch (state) {
				case HttpState.SUCCESS:
					progressBarDialog.dismiss();
					userList = baseApp.userList;
					myFriendsAdapter = new MyFriendsAdapter(MyFriendsActivity.this, userList);
					list_myFriends.setAdapter(myFriendsAdapter);
					break;
				case HttpState.FAILURE:
					progressBarDialog.dismiss();
					isSureDialog = new IsSureDialog(MyFriendsActivity.this,
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
										myFriendsAction.sendGet();
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
							MyFriendsActivity.this,
							getResources().getString(
									R.string.warn_network_not_available),
							Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		});
		myFriendsAction.sendGet();
	}

	@Override
	public void backToActivity() {

		startActivity(new Intent(MyFriendsActivity.this,
				MainFunctionActivity.class));
		finish();
	}

	@Override
	public void onClick(View v) {
		backToActivity();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			backToActivity();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}

}
