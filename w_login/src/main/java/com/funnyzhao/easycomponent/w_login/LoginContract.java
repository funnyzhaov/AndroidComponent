package com.funnyzhao.easycomponent.w_login;

import android.app.Activity;

import com.funnyzhao.easycomponent.commonbase.base.BasePresenter;
import com.funnyzhao.easycomponent.commonbase.base.BaseView;

/**
 * Created by zhaowenjie on 2018/3/21.
 *
 * @desc 登录契约接口，存放Presenter 、View
 */

public interface LoginContract {
	
	interface Presenter extends BasePresenter{
		
		void login();
		
		void startRegisterActivity(Activity from);
		
		void startResetActivity(Activity from);
	}
	
	interface View extends BaseView{
		/**
		 * 显示提示信息,一般为错误或输入不规范的信息
		 * @param tips 信息
		 */
		void showTips(String tips);
		
		/**
		 * @return 电话号码
		 */
		String getPhone();
		
		/**
		 * @return 密码
		 */
		String getPassword();
		
	}
}
