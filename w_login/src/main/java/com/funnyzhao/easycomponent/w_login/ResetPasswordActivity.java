package com.funnyzhao.easycomponent.w_login;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.funnyzhao.easycomponent.commonbase.base.BaseActivity;
import com.funnyzhao.easycomponent.commonbase.commonutil.StatusBarUtil;


/**
 * Created by zhaowenjie on 2018/3/22.
 *
 * @desc 忘记密码页面，引导用户完成密码的找回或更改
 */
@Route(path = "/w_login/ResetPasswordActivity")
public class ResetPasswordActivity extends BaseActivity {
	private TextView mTips;
	
	@Override
	protected void initEventBeforeView() {
	
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.w_login_view_validation_code;
	}
	
	@Override
	protected void initActivityEvent() {
		mTips=findViewById(R.id.tv_code_page_tips);
		StatusBarUtil.setStatusBarColor(this,R.color.w_login_white,true);
		mTips.setText("忘记密码");
	}
}
