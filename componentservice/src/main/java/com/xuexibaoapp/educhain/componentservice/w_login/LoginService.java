package com.xuexibaoapp.educhain.componentservice.w_login;


import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by zhaowenjie on 2018/3/23.
 *
 * @desc 登录组件提供的服务列表
 */

public interface LoginService extends IProvider {
	/**
	 * 返回用户token Key值
 	 * @return
	 */
	String getUserTokenKey();
}
