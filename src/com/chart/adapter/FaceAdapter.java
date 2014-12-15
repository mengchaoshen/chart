package com.chart.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.chart.R;
import com.chart.model.Face;

public class FaceAdapter extends BaseAdapter {

	private List<Face> faceList;
	
	private LayoutInflater inflater;
	
	private ViewHolder holder;
	
	private Context context;
	
	public FaceAdapter(Context context, List<Face> faceList){
		inflater = LayoutInflater.from(context);
		this.faceList = faceList;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		if(null != faceList){
			return faceList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(null != faceList){
			return faceList.get(position);
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
			convertView = inflater.inflate(R.layout.item_face, null);
			holder = new ViewHolder();
			holder.iv_face = (ImageView) convertView.findViewById(R.id.iv_face);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.iv_face.setImageDrawable(context.getResources().getDrawable(faceList.get(position).getUrl()));
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_face;
	}

}
