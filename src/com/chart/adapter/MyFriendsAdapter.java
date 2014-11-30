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
import com.chart.model.User;
import com.chart.util.BaseUtil;

public class MyFriendsAdapter extends BaseAdapter {
	
	private List<User> userList;
	
	private LayoutInflater inflater;
	
	private ViewHolder holder;
	
	public MyFriendsAdapter(Context context, List<User> userList){
		this.userList = userList;
		inflater = LayoutInflater.from(context);
	}

	
	@Override
	public int getCount() {
		if(null != userList){
			return userList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(null != userList){
			return userList.get(position);
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
			convertView = inflater.inflate(R.layout.item_my_friends, null);
			holder = new ViewHolder();
			holder.btn_head = (Button) convertView.findViewById(R.id.btn_head);
			holder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
			holder.txt_studyId = (TextView) convertView.findViewById(R.id.txt_studyId);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.btn_head.setText(BaseUtil.getHead(userList.get(position).getUserName()));
		holder.txt_name.setText(userList.get(position).getUserName());
		holder.txt_studyId.setText(userList.get(position).getStudyId());
		return convertView;
	}
	
	class ViewHolder{
		
		Button btn_head;
		
		TextView txt_name;
		
		TextView txt_studyId;
		
	}

}
