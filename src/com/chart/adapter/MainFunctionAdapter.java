package com.chart.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chart.R;
import com.chart.model.MainFunction;

public class MainFunctionAdapter extends BaseAdapter {
	
	private List<MainFunction> mainFunctionList;
	
	private LayoutInflater inflater;
	
	private ViewHolder holder;
	
	private Context context;

	public MainFunctionAdapter(Context context, List<MainFunction> mainFunctionList){
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.mainFunctionList = mainFunctionList;
	}
	
	@Override
	public int getCount() {
		if(null != mainFunctionList){
			return mainFunctionList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(null != mainFunctionList){
			return mainFunctionList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(null == convertView){
			convertView = inflater.inflate(R.layout.item_fucnction, null);
			holder = new ViewHolder();
			holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
			holder.txt_functionName = (TextView) convertView.findViewById(R.id.txt_functionName);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.img_icon.setImageDrawable(context.getResources().getDrawable(mainFunctionList.get(position).getIconId()));
		holder.txt_functionName.setText(mainFunctionList.get(position).getName());
		
		return convertView;
	}
	
	class ViewHolder{
		
		ImageView img_icon;
		
		TextView txt_functionName;
		
	}

}
