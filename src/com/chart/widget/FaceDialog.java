package com.chart.widget;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.chart.BaseApplication;
import com.chart.R;
import com.chart.adapter.FaceAdapter;
import com.chart.constant.FaceConstant;
import com.chart.interfaces.OnDialogClickListener;
import com.chart.model.Face;
import com.lidroid.xutils.util.LogUtils;

public class FaceDialog extends Dialog implements OnItemClickListener{

	private GridView gd_face;
	
	private OnDialogClickListener onDialogClickListener;
	
	private List<Face> faceList;
	
	private FaceAdapter faceAdapter;
	
	private Context context;
	
	public FaceDialog(Context context) {
		super(context, R.style.MyDialog);
		setContentView(R.layout.dialog_face);
		this.context = context;
		findViewById();
		init();
	}
	
	public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener){
		this.onDialogClickListener = onDialogClickListener;
	}
	
	private void findViewById(){
		gd_face = (GridView) findViewById(R.id.gd_face);
	}
	
	private void init(){
		faceList = FaceConstant.faceList;
		LogUtils.e(faceList.size()+"'");
		faceAdapter = new FaceAdapter(context, faceList);
		gd_face.setAdapter(faceAdapter);
		gd_face.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		((BaseApplication) context.getApplicationContext()).url = ((Face) parent.getItemAtPosition(position)).getUrl();
		((BaseApplication) context.getApplicationContext()).spTxt = ((Face) parent.getItemAtPosition(position)).getSpTxt();
		onDialogClickListener.onDialogClick(view.getId(), null);
	}

}
