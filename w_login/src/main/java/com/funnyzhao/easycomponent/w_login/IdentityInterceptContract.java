package com.funnyzhao.easycomponent.w_login;

import com.funnyzhao.easycomponent.commonbase.base.BasePresenter;
import com.funnyzhao.easycomponent.commonbase.base.BaseView;

/**
 * Created by zhaowenjie on 2018/3/27.
 *
 * @desc
 */

public interface IdentityInterceptContract {
	interface Presenter extends BasePresenter{
		/**
		 * 获取图形验证码数据
		 */
		void getCodeData();
		
		/**
		 * 检查输入code是否正确
		 */
		void checkInput();
		
		/**
		 * finish
		 */
		void closeActivity();
	}
	
	interface View extends BaseView{
		
		String getInputCode();
		
		void showMsg(String msg);
	}
}
