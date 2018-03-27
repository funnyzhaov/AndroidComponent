package com.xuexibaoapp.educhain.w_login.serviceimpl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xuexibaoapp.educhain.componentservice.w_login.LoginService;
import com.xuexibaoapp.educhain.w_login.model.UserConstant;

/**
 * Created by zhaowenjie on 2018/3/23.
 *
 * @desc
 */
@Route(path ="/w_login/token")
public class LoginServiceImpl implements LoginService{
		
		private Context mContext;
		@Override
		public String getUserTokenKey() {
			return UserConstant.TOKEN;
		}
		
		@Override
		public void init(Context context) {
			mContext=context;
		}
}
