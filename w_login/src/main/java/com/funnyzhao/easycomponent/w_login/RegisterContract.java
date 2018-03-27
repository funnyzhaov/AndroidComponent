package com.funnyzhao.easycomponent.w_login;

import com.funnyzhao.easycomponent.commonbase.base.BasePresenter;
import com.funnyzhao.easycomponent.commonbase.base.BaseView;

/**
 * Created by zhaowenjie on 2018/3/23.
 *
 * @desc
 */

public interface RegisterContract {
	
	interface Presenter extends BasePresenter{
		/**
		 * 检查验证码
		 */
		void checkPhoneCode();
		/**
		 * 注册
		 */
		void register();
		
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
		 * @return 验证码
		 */
		String getCode();
		
	}
}
