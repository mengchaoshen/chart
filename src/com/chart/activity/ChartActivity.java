package com.chart.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.chart.BaseActivity;
import com.chart.R;
import com.chart.adapter.ChartAdapter;
import com.chart.dao.BaseDao;
import com.chart.model.ChartItem;
import com.chart.widget.ProgressBarDialog;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

public class ChartActivity extends BaseActivity implements OnClickListener, Runnable{

	private ListView list_chart;
	
	private EditText edtxt_text;
	
	private Button btn_send;
	
	private Button btn_face;
	
	private ChartAdapter chartAdapter;
	
	private ProgressBarDialog progressBarDialog;
	
	private List<ChartItem> chartItemList;
	
	private Handler handler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			if(msg.what == 1){
				chartAdapter = new ChartAdapter(ChartActivity.this, chartItemList);
				list_chart.setAdapter(chartAdapter);
				progressBarDialog.dismiss();
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
		progressBarDialog = new ProgressBarDialog(ChartActivity.this);
		btn_send.setOnClickListener(this);
		btn_face.setOnClickListener(this);
		progressBarDialog.show();
		new Thread(this).start();
	}

	@Override
	public void backToActivity() {

		startActivity(new Intent(ChartActivity.this, MainFunctionActivity.class));
		finish();
	}

	@Override
	public void onClick(View arg0) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		Message msg = new Message();
		
		try {
			chartItemList = (List<ChartItem>) BaseDao.query(ChartActivity.this, Selector.from(ChartItem.class).where("chartObject", "=", "group1"));
		} catch (DbException e) {
			e.printStackTrace();
		}
		msg.what = 1;
		handler.sendMessage(msg);
	}

}
