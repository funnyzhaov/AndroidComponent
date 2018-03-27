package com.funnyzhao.easycomponent.photo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.funnyzhao.easycomponent.photo.load.XLImageLoad;


public class TestPhotoActivity extends AppCompatActivity {
	Button mBtnLoadGif,mBtnLoadNet;
	ImageView iv;
	
	//net
	String url="http://s1.dwstatic.com/group1/M00/B9/01/be2ffcf015a23722b81df5e729036a90.jpg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_photo);
		iv=findViewById(R.id.iv_test);
		mBtnLoadGif=findViewById(R.id.btn_load_gif);
		mBtnLoadNet=findViewById(R.id.btn_load_net);
		mBtnLoadGif.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				XLImageLoad.getInstance().loadGif(TestPhotoActivity.this,R.drawable.photo_app_start,iv);
			}
		});
		mBtnLoadNet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				XLImageLoad.getInstance().loadImage(TestPhotoActivity.this,url,iv);
			}
		});
	}
}
