package com.funnyzhao.easycomponent.w_login;

import android.content.Context;

import com.funnyzhao.easycomponent.network.exception.ExceptionHandle;
import com.funnyzhao.easycomponent.network.manager.BaseObserver;
import com.funnyzhao.easycomponent.network.manager.RetrofitManager;
import com.funnyzhao.easycomponent.network.response.BaseResponse;
import com.funnyzhao.easycomponent.network.response.BaseResult;
import com.funnyzhao.easycomponent.w_login.model.UserConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaowenjie on 2018/3/23.
 *
 * @desc
 */

public class RegisterPresenterImpl implements RegisterContract.Presenter {
	private RegisterContract.View mView;
	private Context mContext;
	public RegisterPresenterImpl(RegisterContract.View view, Context context){
		mView=view;
		mContext=context;
	}
	
	
	@Override
	public void checkPhoneCode() {
		//if true -->通知UI刷新填写密码-->调用register注册
		Map<String,String> codeMap= new HashMap<>();
		codeMap.put(UserConstant.PHONE,mView.getPhone());
		codeMap.put(UserConstant.VCODE,mView.getCode());
		RetrofitManager.getInstance(mContext).createBaseApi().filedPost("user/api/checkPhone", codeMap, new BaseObserver<BaseResponse<BaseResult>>(mContext) {
			@Override
			public void _onNext(BaseResponse<BaseResult> baseResultBaseResponse) {
				if(baseResultBaseResponse.getStatus()==0){
					//刷新UI，完善密码
				}else{
					mView.showTips(baseResultBaseResponse.getMsg());
				}
			}
			
			@Override
			public void _onError(ExceptionHandle.ResponseThrowable throwable) {
				mView.showTips(throwable.message);
			}
			
			@Override
			protected void showDialog() {
			
			}
			
			@Override
			protected void hideDialog() {
			
			}
		});
		
		
	}
	
	@Override
	public void register() {
	
	
	
	}
}
