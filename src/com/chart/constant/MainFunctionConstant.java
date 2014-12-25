package com.chart.constant;

import java.util.ArrayList;
import java.util.List;

import com.chart.R;
import com.chart.model.MainFunction;

public class MainFunctionConstant {

	public static List<MainFunction> getMainFunctionList(){
		List<MainFunction> list = new ArrayList<MainFunction>();
		MainFunction mainFunction1 = new MainFunction(1, "群聊", R.drawable.menu_tax_detail_select);
//		MainFunction mainFunction2 = new MainFunction(2, "私聊", R.drawable.menu_tax_list_apply);
		MainFunction mainFunction3 = new MainFunction(3, "我的好友", R.drawable.menu_my_tax_list);
		MainFunction mainFunction4 = new MainFunction(4, "群公告", R.drawable.menu_my_tax_list);
//		MainFunction mainFunction5 = new MainFunction(5, "上传公告", R.drawable.menu_list_check);
		list.add(mainFunction1);
//		list.add(mainFunction2);
		list.add(mainFunction3);
		list.add(mainFunction4);
//		list.add(mainFunction5);
		return list;
	}
}
