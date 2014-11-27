package com.chart.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.chart.R;
import com.chart.constant.FaceConstant;
import com.chart.model.ChartItem;

public class ChartAdapter extends BaseAdapter {

	private List<ChartItem> chartItemList;

	private LayoutInflater inflater;

	private ViewHolder holder;
	
	private Context context;

	public ChartAdapter(Context context, List<ChartItem> chartItemList) {
		inflater = LayoutInflater.from(context);
		this.chartItemList = chartItemList;
		this.context= context;
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
			holder.txt_chartText.setGravity(Gravity.LEFT);
		}else{
			holder.btn_myHead.setVisibility(View.VISIBLE);
			holder.btn_myHead.setText(chartItemList.get(position).getHead());
			holder.btn_otherHead.setVisibility(View.GONE);
			holder.txt_chartText.setGravity(Gravity.RIGHT);
		}
		holder.txt_chartText.setText(toface(chartItemList.get(position).getChartText()));
		return convertView;
	}

	class ViewHolder {

		Button btn_otherHead;

		Button btn_myHead;

		TextView txt_chartText;
	}
	
	/**
	 * 将规定字符串转化为表情
	 * @param text
	 * @return
	 */
	private CharSequence  toface(String text){
		Map<String, Integer> map = FaceConstant.map;
		SpannableStringBuilder ssb = new SpannableStringBuilder();
		for(int i = 0; i<text.length(); i++){
			if (text.charAt(i) == '/') {
				String mayFace = "/" + text.charAt(i + 1) + text.charAt(i + 2);
				if(null != map.get(mayFace)){
					Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), map.get(mayFace));
					ImageSpan is = new ImageSpan(context, bitmap);
					SpannableString ss = new SpannableString(mayFace);
					ss.setSpan(is, 0, 3, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
					ssb.append(ss);
					i+=2;
				}
			}else{
				ssb.append(text.charAt(i));
			}
		}
		return ssb;
	}

}
