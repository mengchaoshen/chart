package com.chart.activity;

import java.io.File;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import com.chart.BaseActivity;
import com.chart.R;
import com.chart.action.UploadAction;
import com.chart.constant.ActionConstant;
import com.chart.constant.GlobConstant;
import com.chart.constant.HttpState;
import com.chart.interfaces.HttpCallback;
import com.chart.interfaces.OnDialogClickListener;
import com.chart.util.BitmapUtil;
import com.chart.widget.IsSureDialog;
import com.chart.widget.ProgressBarDialog;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.util.LogUtils;

public class UploadActivity extends BaseActivity implements OnClickListener {

	private Button btn_back;

	private LinearLayout layout_openCamear;

	private LinearLayout layout_fromAlbums;

	private ImageView img_up;

	private EditText edtxt_description;

	private Button btn_upload;
	
	private String photoName;
	
	private String photo;
	
	private ProgressBarDialog progressBarDialog;

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
		progressBarDialog = new ProgressBarDialog(UploadActivity.this);
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
			upload();
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

	private void upload(){
		progressBarDialog.show();
		RequestParams requestParams = new RequestParams();
		
		requestParams.addBodyParameter("studyId", baseApp.user.getStudyId());
		requestParams.addBodyParameter("photo", photo);
		requestParams.addBodyParameter("description", edtxt_description.getText().toString());
		requestParams.addBodyParameter("sendName", baseApp.user.getUserName());
		
		LogUtils.e(baseApp.user.getStudyId());
		LogUtils.e(photo);
		LogUtils.e(edtxt_description.getText().toString());
		LogUtils.e(baseApp.user.getUserName());
		UploadAction uploadAction = new UploadAction(UploadActivity.this, ActionConstant.UPLOAD_AN, requestParams);
		uploadAction.setHttpCallback(new HttpCallback() {
			
			@Override
			public void onCallback(int state, String result) {

				switch (state) {
				case HttpState.SUCCESS:
					progressBarDialog.dismiss();
					Toast.makeText(
							UploadActivity.this,
							"上传成功",
							Toast.LENGTH_SHORT).show();
					break;
				case HttpState.FAILURE:
					progressBarDialog.dismiss();
					Toast.makeText(
							UploadActivity.this,
							"上传失败",
							Toast.LENGTH_SHORT).show();
					break;
				case HttpState.NETWORK_AVAILABLE:
					progressBarDialog.dismiss();
					Toast.makeText(
							UploadActivity.this,
							getResources().getString(
									R.string.warn_network_not_available),
							Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			
			}
		});
		uploadAction.sendPost();
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
		String path = null;
		if (GlobConstant.FROM_ALBUMS == requestCode) {
			Uri uri = data.getData();
			// bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = managedQuery(uri, proj, null, null, null);
			int index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			path = cursor.getString(index);
			img_up.setImageBitmap(BitmapUtil.getSmallBitmap(path));
		} else if (GlobConstant.TAKE_PHOTO == requestCode) {
			path = GlobConstant.PACKAGE_NAME+"/"+photoName;
			img_up.setImageBitmap(BitmapUtil.getSmallBitmap(GlobConstant.PACKAGE_NAME+"/"+photoName));
		}
		photo = BitmapUtil.bitmapToString(path);
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
