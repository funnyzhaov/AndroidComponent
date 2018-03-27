package com.xuexibaoapp.educhain.w_login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xuexibaoapp.educhain.network.exception.ExceptionHandle;
import com.xuexibaoapp.educhain.network.manager.BaseObserver;
import com.xuexibaoapp.educhain.network.manager.RetrofitManager;
import com.xuexibaoapp.educhain.network.response.BaseResponse;
import com.xuexibaoapp.educhain.network.response.BaseResult;
import com.xuexibaoapp.educhain.network.utils.log.NetLog;
import com.xuexibaoapp.educhain.w_login.model.UserConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaowenjie on 2018/3/21.
 *
 * @desc
 */

public class LoginPresenterImpl implements LoginContract.Presenter {
	private LoginContract.View mView;
	private Context mContext;
	
	public LoginPresenterImpl(LoginContract.View view, Context context){
		mView=view;
		mContext=context;
	}
	@Override
	public void login() {
		Map<String,String> useMap=new HashMap<>();
		useMap.put(UserConstant.PASSWORD,mView.getPassword());
		useMap.put(UserConstant.PHONE,mView.getPhone());
		RetrofitManager.getInstance(mContext).createBaseApi().filedPost(
				"user/api/loginByPhone", useMap, new BaseObserver<BaseResponse<BaseResult>>(mContext) {
					@Override
					public void _onNext(BaseResponse<BaseResult> baseResultBaseResponse) {
						if(baseResultBaseResponse.isSuccess() || baseResultBaseResponse.getStatus()==0){
							NetLog.i(baseResultBaseResponse.getResult().getToken());
							String token=baseResultBaseResponse.getResult().getToken();
							//进入首页
							ARouter.getInstance().build("/w_home/MainActivity").withString(UserConstant.TOKEN,token);
						}else{
							mView.showTips(baseResultBaseResponse.getMsg());
							NetLog.d(baseResultBaseResponse.getMsg()+" "+baseResultBaseResponse.getStatus());
						}
					}
					
					@Override
					public void _onError(ExceptionHandle.ResponseThrowable throwable) {
						mView.showTips(throwable.message);
						NetLog.d(throwable.message+"  "+throwable.status);
					}
					
					@Override
					protected void showDialog() {
					
					}
					
					@Override
					protected void hideDialog() {
					
					}
				}
		);
	
	}
	
	@Override
	public void startRegisterActivity(Activity from) {
		Intent intent=new Intent(from,IdentityInterceptActivity.class);
		intent.putExtra(UserConstant.TOCODE,UserConstant.TOREGISTER);
		from.startActivity(intent);
		
	}
	
	@Override
	public void startResetActivity(Activity from) {
		Intent intent=new Intent(from,IdentityInterceptActivity.class);
		intent.putExtra(UserConstant.TOCODE,UserConstant.TOFORGETPASSWORD);
		from.startActivity(intent);
	}
}
