package com.chart.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chart.R;
import com.chart.model.Announcement;
import com.chart.util.BitmapUtil;
import com.chart.util.DateTimeUtil;

public class AnnAdapter extends BaseAdapter {

	private List<Announcement> annList;
	
	private LayoutInflater inflater; 
	
	private ViewHolder holder;
	
	public AnnAdapter(Context context, List<Announcement> annList){
		inflater = LayoutInflater.from(context);
		this.annList = annList;
	}
	
	@Override
	public int getCount() {
		if(null != annList){
			return annList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(null != annList){
			return annList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	@SuppressWarnings("deprecation")
	public View getView(int position, View convertView, ViewGroup parent) {
		if(null == convertView){
			convertView = inflater.inflate(R.layout.item_ann, null);
			holder = new ViewHolder();
			holder.txt_date =  (TextView) convertView.findViewById(R.id.txt_date);
			holder.txt_description =  (TextView) convertView.findViewById(R.id.txt_description);
			holder.txt_studyId =  (TextView) convertView.findViewById(R.id.txt_studyId);
			holder.img_ann =  (ImageView) convertView.findViewById(R.id.img_ann);
			convertView.setTag(holder);
		}else{
			
			holder = (ViewHolder) convertView.getTag();
		}
		
		Drawable drawable = new BitmapDrawable(BitmapUtil.convertStringToIcon(annList.get(position).getPhoto()));
		holder.txt_date.setText(DateTimeUtil.getCurrentDate(annList.get(position).getTime()));
		holder.img_ann.setImageDrawable(drawable);
		holder.txt_studyId.setText(annList.get(position).getSendName());
		holder.txt_description.setText(annList.get(position).getDescription());
		return convertView;
	}
	
	class ViewHolder{
		
		TextView txt_date;
		
		TextView txt_description;
		
		TextView txt_studyId;
		
		ImageView img_ann;
	}

}
