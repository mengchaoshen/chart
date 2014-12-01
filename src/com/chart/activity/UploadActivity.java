package com.chart.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chart.BaseActivity;
import com.chart.R;
import com.chart.constant.GlobConstant;
import com.chart.util.BitmapUtil;

public class UploadActivity extends BaseActivity implements OnClickListener {

	private Button btn_back;

	private LinearLayout layout_openCamear;

	private LinearLayout layout_fromAlbums;

	private ImageView img_up;

	private EditText edtxt_description;

	private Button btn_upload;
	
	private String photoName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_image);
		findViewById();
		init();
	}

	@Override
	public void findViewById() {
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_upload = (Button) findViewById(R.id.btn_upload);
		layout_openCamear = (LinearLayout) findViewById(R.id.layout_openCamear);
		layout_fromAlbums = (LinearLayout) findViewById(R.id.layout_fromAlbums);
		edtxt_description = (EditText) findViewById(R.id.edtxt_description);
		img_up = (ImageView) findViewById(R.id.img_up);
	}

	@Override
	public void init() {
		btn_back.setOnClickListener(this);
		btn_upload.setOnClickListener(this);
		layout_openCamear.setOnClickListener(this);
		layout_fromAlbums.setOnClickListener(this);
	}

	@Override
	public void backToActivity() {
		startActivity(new Intent(UploadActivity.this,
				MainFunctionActivity.class));
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			backToActivity();
			break;
		case R.id.btn_upload:

			break;
		case R.id.layout_openCamear:
			takePhoto();
			break;
		case R.id.layout_fromAlbums:
			fromAlbums();
			break;
		default:
			break;
		}
	}

	private void takePhoto() {
		File d  = new File(GlobConstant.PACKAGE_NAME); 
		if(!d.exists()){
			d.mkdirs();
		}
		photoName = BitmapUtil.getPhotoName();
		Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File f = new File(GlobConstant.PACKAGE_NAME, photoName);// localTempImgDir和localTempImageFileName是自己定义的名字
		Uri u = Uri.fromFile(f);
		takePhoto.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
		takePhoto.putExtra(MediaStore.EXTRA_OUTPUT, u);
		startActivityForResult(takePhoto, GlobConstant.TAKE_PHOTO);
	}

	private void fromAlbums() {
		Intent getAlbums = new Intent(Intent.ACTION_GET_CONTENT);
		getAlbums.setType(GlobConstant.IMAGE_TYPE);
		startActivityForResult(getAlbums, GlobConstant.FROM_ALBUMS);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		// Bitmap bitmap =null;
		// ContentResolver resolver = getContentResolver();
		if (GlobConstant.FROM_ALBUMS == requestCode) {
			Uri uri = data.getData();
			// bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = managedQuery(uri, proj, null, null, null);
			int index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			String path = cursor.getString(index);
			img_up.setImageBitmap(BitmapUtil.getSmallBitmap(path));
		} else if (GlobConstant.TAKE_PHOTO == requestCode) {
			img_up.setImageBitmap(BitmapUtil.getSmallBitmap(GlobConstant.PACKAGE_NAME+"/"+photoName));
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			backToActivity();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
