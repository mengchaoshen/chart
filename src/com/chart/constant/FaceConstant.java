package com.chart.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chart.R;
import com.chart.model.Face;


public class FaceConstant {

	public static Map<String, Face> map = new HashMap<String, Face>();
	
	public static List<Face> faceList = new ArrayList<Face>();
	
	static {
		faceList.add(new Face(1, R.drawable.face_1, "/f01"));
		faceList.add(new Face(2, R.drawable.face_2, "/f02"));
		faceList.add(new Face(3, R.drawable.face_3, "/f03"));
		faceList.add(new Face(4, R.drawable.face_4, "/f04"));
		faceList.add(new Face(5, R.drawable.face_5, "/f05"));
		faceList.add(new Face(6, R.drawable.face_6, "/f06"));
		faceList.add(new Face(7, R.drawable.face_7, "/f07"));
		faceList.add(new Face(8, R.drawable.face_8, "/f08"));
		faceList.add(new Face(9, R.drawable.face_9, "/f09"));
		faceList.add(new Face(10, R.drawable.face_10, "/f10"));
		
		map.put("/f01", new Face(1, R.drawable.face_1, "/f01"));
		map.put("/f02", new Face(2, R.drawable.face_2, "/f02"));
		map.put("/f03", new Face(3, R.drawable.face_3, "/f03"));
		map.put("/f04", new Face(4, R.drawable.face_4, "/f04"));
		map.put("/f05", new Face(5, R.drawable.face_5, "/f05"));
		map.put("/f06", new Face(6, R.drawable.face_6, "/f06"));
		map.put("/f07", new Face(7, R.drawable.face_7, "/f07"));
		map.put("/f08", new Face(8, R.drawable.face_8, "/f08"));
		map.put("/f09", new Face(9, R.drawable.face_9, "/f09"));
		map.put("/f10", new Face(1, R.drawable.face_10, "/f10"));
	}
}
