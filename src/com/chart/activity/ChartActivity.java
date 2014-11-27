package com.chart.activity;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chart.BaseActivity;
import com.chart.R;
import com.chart.action.SendMessageAction;
import com.chart.adapter.ChartAdapter;
import com.chart.constant.ActionConstant;
import com.chart.constant.GlobConstant;
import com.chart.constant.HttpState;
import com.chart.dao.BaseDao;
import com.chart.interfaces.HttpCallback;
import com.chart.model.ChartItem;
import com.chart.widget.ProgressBarDialog;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.util.LogUtils;

public class ChartActivity extends BaseActivity implements OnClickListener,
		Runnable {

	private ListView list_chart;

	private EditText edtxt_text;

	private Button btn_send;

	private Button btn_face;

	private ChartAdapter chartAdapter;

	private ProgressBarDialog progressBarDialog;

	private List<ChartItem> chartItemList;

	private String chartType;

	private Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == 1) {
				if(null != chartItemList && chartItemList.size() > 0 ){
					chartAdapter = new ChartAdapter(ChartActivity.this,
							chartItemList);
					list_chart.setAdapter(chartAdapter);
					list_chart.setSelection(chartItemList.size() - 1);
				}
				if (progressBarDialog.isShowing()) {
					progressBarDialog.dismiss();
				}
			}
			return false;
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		findViewById();
		init();
		initReceiver();
	}

	private void initReceiver() {
		registerReceiver(new MyReceiver(), new IntentFilter(
				GlobConstant.UPDATE_RECEIVER));
	}

	class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			new Thread(ChartActivity.this).start();
		}

	}

	@Override
	public void findViewById() {
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_face = (Button) findViewById(R.id.btn_face);
		list_chart = (ListView) findViewById(R.id.list_chart);
		edtxt_text = (EditText) findViewById(R.id.edtxt_text);
	}

	@Override
	public void init() {
		chartType = GlobConstant.GROPU;
		progressBarDialog = new ProgressBarDialog(ChartActivity.this);
		btn_send.setOnClickListener(this);
		btn_face.setOnClickListener(this);
		edtxt_text.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					list_chart.setSelection(chartItemList.size() - 1);
				}
			}
		});
		progressBarDialog.show();
		new Thread(this).start();
	}

	@Override
	public void backToActivity() {

		startActivity(new Intent(ChartActivity.this, MainFunctionActivity.class));
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:// 发送
			if (null != edtxt_text.getText().toString()
					&& !"".equals(edtxt_text.getText().toString())) {
				ChartItem chartItem = new ChartItem(baseApp.user.getStudyId(),
						"", edtxt_text.getText().toString(), false, chartType,
						"");

				sendMessage(JSON.toJSONString(chartItem));
				list_chart.setSelection(chartItemList.size() - 1);
			}
			break;
		case R.id.btn_face:// 表情

			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			ImageSpan is = new ImageSpan(ChartActivity.this, bitmap);
			SpannableString ss = new SpannableString("/f1");
			ss.setSpan(is, 0, 3, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
			edtxt_text.append(ss);
			break;
		default:
			break;
		}
	}

	/**
	 * 发消息
	 */
	private void sendMessage(String json) {
		LogUtils.e("发出的：" + json);
		RequestParams sendParams = new RequestParams();
		sendParams.setContentType("UTF-8");
		sendParams.addQueryStringParameter("json", json);

		SendMessageAction sendMessageAction = new SendMessageAction(
				ChartActivity.this, ActionConstant.SEND_MESSAGE, sendParams);
		sendMessageAction.setHttpCallback(new HttpCallback() {

			@Override
			public void onCallback(int state, String result) {
				switch (state) {
				case HttpState.SUCCESS:
					if (baseApp.baseJson.isSuccess()) {
						// Toast.makeText(ChartActivity.this,
						// getResources().getString(R.string.login_success),
						// Toast.LENGTH_SHORT).show();
						// 发送成功
						progressBarDialog.show();
						new Thread(ChartActivity.this).start();
					} else {// 发送失败
						Toast.makeText(ChartActivity.this,
								getResources().getString(R.string.send_fail),
								Toast.LENGTH_SHORT).show();
					}
					edtxt_text.setText(null);
					break;
				case HttpState.FAILURE:
					break;
				case HttpState.NETWORK_AVAILABLE:
					progressBarDialog.dismiss();
					Toast.makeText(
							ChartActivity.this,
							getResources().getString(
									R.string.warn_network_not_available),
							Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		});
		sendMessageAction.sendPost();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		Message msg = new Message();

		try {
			chartItemList = (List<ChartItem>) BaseDao.query(
					ChartActivity.this,
					Selector.from(ChartItem.class)
							.where("chartObject", "=", "group1")
							.and("chartType", "=", "0")
							.and("studyId", "=", baseApp.user.getStudyId()));
		} catch (DbException e) {
			e.printStackTrace();
		}
		msg.what = 1;
		handler.sendMessage(msg);
	}

}
