package com.funnyzhao.easycomponent.commonbase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by zhaowenjie on 2018/3/21.
 *
 * @desc Activity基础类
 * 1.butter knife的初始化工作
 */

public abstract class BaseActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initEventBeforeView();
		setContentView(getLayoutId());
		initActivityEvent();
	}
	
	
	protected abstract void initEventBeforeView();
	
	protected abstract int getLayoutId();
	
	protected abstract void initActivityEvent();
	
}
