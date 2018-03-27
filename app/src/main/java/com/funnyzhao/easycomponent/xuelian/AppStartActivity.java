package com.funnyzhao.easycomponent.xuelian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;

import com.funnyzhao.easycomponent.commonbase.base.BaseActivity;
import com.funnyzhao.easycomponent.commonbase.commonutil.CountDownUtil;
import com.funnyzhao.easycomponent.commonbase.commonutil.StatusBarUtil;
import com.funnyzhao.easycomponent.photo.load.XLImageLoad;

import io.reactivex.disposables.Disposable;

/**
 * Created by zhaowenjie on 2018/3/26.
 *
 * @desc
 */

public class AppStartActivity extends BaseActivity {
	
	//iv
	private ImageView mIvGif;
	private CountDownUtil mCountDownUtil;
	private Disposable mdDisposable;
	
	private SharedPreferences mSp;
	
	@Override
	protected void initEventBeforeView() {
		mCountDownUtil=new CountDownUtil();
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.activity_appstart;
	}
	
	@Override
	protected void initActivityEvent() {
		mIvGif=findViewById(R.id.iv_app_start_gif);
		StatusBarUtil.setStatusBarColor(this,R.color.appStartColor,false);
		enterMain();
	}
	
	private void enterMain(){
		if(isFirststart()){
			loadAni();
			startAnim();
		}else {
			startMain();
		}
	}
	
	private void loadAni(){
		XLImageLoad.getInstance().loadGif(this, com.funnyzhao.easycomponent.photo.R.drawable.photo_app_start,mIvGif);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mdDisposable!=null && !mdDisposable.isDisposed()){
			mdDisposable.dispose();
		}
	}
	
	//执行动画
	private void startAnim(){
		mCountDownUtil.countDown(mdDisposable, 2, new CountDownUtil.CountDownCall() {
			@Override
			public void start() {
			}
			
			@Override
			public void onNext(Long aLong) {
			}
			
			@Override
			public void onComplete() {
				startMain();
			}
		});
	}
	
	//是否为第一次启动
	private boolean isFirststart(){
		mSp=getSharedPreferences("config", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=mSp.edit();
		if(mSp.getInt("start",0)!=1){
			editor.putInt("start",1);
			editor.apply();
			return true;
		}else {
			return false;
		}
	}
	//启动主页
	private void startMain(){
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
