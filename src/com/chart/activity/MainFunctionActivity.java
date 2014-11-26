package com.chart.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.chart.BaseActivity;
import com.chart.R;
import com.chart.adapter.MainFunctionAdapter;
import com.chart.constant.MainFunctionConstant;
import com.chart.interfaces.OnDialogClickListener;
import com.chart.model.MainFunction;
import com.chart.widget.IsSureDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainFunctionActivity extends BaseActivity implements
		OnItemClickListener, OnTouchListener, OnDialogClickListener {

	@ViewInject(R.id.btn_back)
	private Button btn_back;

	private GridView gd_function;

	private MainFunctionAdapter adapter;

	private List<MainFunction> mainFunctionList;

	private IsSureDialog isSureDialog;

	private DialogType dialogType;
	
	private TextView txt_userName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		findViewById();
		init();
	}

	@OnClick({ R.id.btn_back })
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			backToActivity();
			break;
		default:
			break;
		}
	}

	@Override
	public void findViewById() {
		txt_userName = (TextView) findViewById(R.id.txt_userName);
		gd_function = (GridView) findViewById(R.id.gd_function);
		gd_function.setOnItemClickListener(this);
	}

	@Override
	public void init() {
		txt_userName.setText("你好，" + baseApp.user.getUserName());
		mainFunctionList = MainFunctionConstant.getMainFunctionList();
		adapter = new MainFunctionAdapter(MainFunctionActivity.this,
				mainFunctionList);
		gd_function.setAdapter(adapter);
		gd_function.setOnTouchListener(this);
	}

	@Override
	public void backToActivity() {
		isSureDialog = new IsSureDialog(MainFunctionActivity.this,
				"确定要退出，重新登录吗？");
		dialogType = DialogType.exit;
		isSureDialog.setOnDialogClickListener(this);
		isSureDialog.show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (((MainFunction) parent.getItemAtPosition(position)).getId()) {
		case 1:// 群聊
			startActivity(new Intent(MainFunctionActivity.this, ChartActivity.class));
			finish();
			break;
		case 2:// 纳税清单申请
			break;
		case 3:// 我的纳税清单
			toMyTaxList();
			break;
		case 4:// 清单在线校验
			break;
		default:
			break;
		}
	}

	/**
	 * 我的纳税清单
	 */
	private void toMyTaxList() {}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.gd_function:
			// 禁止GridView滚动
			return MotionEvent.ACTION_MOVE == event.getAction() ? true : false;
		default:
			break;
		}
		return false;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			backToActivity();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onDialogClick(int viewId, Object o) {
		switch (viewId) {
		case R.id.btn_sure:
			if (dialogType.equals(DialogType.exit)) {
				startActivity(new Intent(MainFunctionActivity.this,
						LoginActivity.class));
				overridePendingTransition(R.anim.in_from_left,
						R.anim.out_to_right);
				finish();
				isSureDialog.dismiss();
			} else if (dialogType.equals(DialogType.http)) {
				// TODO
			}
			break;
		case R.id.btn_cancel:
			isSureDialog.dismiss();
			break;
		default:
			break;
		}
	}

	enum DialogType {
		exit, http
	}

}
