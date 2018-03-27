package com.funnyzhao.easycomponent.w_login;

import android.content.Context;

/**
 * Created by zhaowenjie on 2018/3/27.
 *
 * @desc
 */

public class IdentityIPresenterImpl implements IdentityInterceptContract.Presenter {
	
	private Context mContext;
	private IdentityInterceptContract.View mView;
	public IdentityIPresenterImpl(Context context,IdentityInterceptContract.View view){
		mContext=context;
		mView=view;
	}
	@Override
	public void getCodeData() {
	
	}
	
	@Override
	public void checkInput() {
	
	}
	
	@Override
	public void closeActivity() {
	
	}
}
