package com.chart.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.chart.R;
import com.chart.model.ChartItem;

public class ChartAdapter extends BaseAdapter {

	private List<ChartItem> chartItemList;

	private LayoutInflater inflater;

	private ViewHolder holder;

	public ChartAdapter(Context context, List<ChartItem> chartItemList) {
		inflater = LayoutInflater.from(context);
		this.chartItemList = chartItemList;
	}

	@Override
	public int getCount() {
		if (null != chartItemList) {
			return chartItemList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		if (null != chartItemList) {
			return chartItemList.get(arg0);
		}
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.item_chart, null);
			holder = new ViewHolder();
			holder.btn_otherHead = (Button) convertView
					.findViewById(R.id.btn_otherHead);
			holder.btn_myHead = (Button) convertView
					.findViewById(R.id.btn_myHead);
			holder.txt_chartText = (TextView) convertView
					.findViewById(R.id.txt_chartText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(chartItemList.get(position).isOther()){
			holder.btn_otherHead.setVisibility(View.VISIBLE);
			holder.btn_otherHead.setText(chartItemList.get(position).getHead());
			holder.btn_myHead.setVisibility(View.GONE);
		}else{
			holder.btn_myHead.setVisibility(View.VISIBLE);
			holder.btn_myHead.setText(chartItemList.get(position).getHead());
			holder.btn_otherHead.setVisibility(View.GONE);
		}
		holder.txt_chartText.setText(chartItemList.get(position).getChartText());

		return convertView;
	}

	class ViewHolder {

		Button btn_otherHead;

		Button btn_myHead;

		TextView txt_chartText;
	}

}
