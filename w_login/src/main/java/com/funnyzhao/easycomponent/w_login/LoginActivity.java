package com.funnyzhao.easycomponent.w_login;


import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.funnyzhao.easycomponent.commonbase.base.BaseActivity;
import com.funnyzhao.easycomponent.commonbase.commonutil.StatusBarUtil;
import com.funnyzhao.easycomponent.w_login.utils.VariableButtonUtil;

@Route(path = "/w_login/LoginActivity")
public class LoginActivity extends BaseActivity implements LoginContract.View,View.OnClickListener{
	//P
	private LoginPresenterImpl mLoginPresenter;
	//控件
	EditText etInputPhone;
	EditText etInputPassword;
	Button btnLogin;
	TextView tvForgetPassword;
	TextView tvRegister;
	
	//Drawable资源
	Drawable btnLoginNormalD;
	Drawable btnLoginActiveD;
	
	//Color
	int btnLoginNormalC; //正常颜色
	int btnLoginActiveC;  //可点击颜色
	int btnLoginPressedC; //点击后颜色
	
	
	@Override
	protected void initEventBeforeView() {
		StatusBarUtil.setStatusBarColor(this,R.color.w_login_white,true);
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.w_login_activity_login;
	}
	
	@Override
	protected void initActivityEvent() {
		//控件
		etInputPhone=findViewById(R.id.et_input_phone);
		etInputPassword=findViewById(R.id.et_input_password);
		btnLogin=findViewById(R.id.btn_login);
		tvForgetPassword=findViewById(R.id.tv_forget_password);
		tvRegister=findViewById(R.id.tv_register);
		mLoginPresenter=new LoginPresenterImpl(this,this);
		loadRes();
		initListener();
	}
	
	@Override
	public void showTips(String tips) {
		Toast.makeText(this,tips,Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public String getPhone() {
		return etInputPhone.getText().toString();
	}
	
	@Override
	public String getPassword() {
		return etInputPassword.getText().toString();
	}
	
	/**
	 * 加载资源
	 */
	private void loadRes(){
		btnLoginNormalD=getDrawable(R.drawable.w_login_login_btn_normal);
		btnLoginActiveD=getDrawable(R.drawable.w_login_login_btn_active);
		
		btnLoginNormalC=getResources().getColor(R.color.w_login_btn_login_normal);
		btnLoginActiveC=getResources().getColor(R.color.w_login_btn_login_active);
		btnLoginPressedC=getResources().getColor(R.color.w_login_btn_login_pressed);
	}
	/**
	 * 初始化监听事件
	 */
	private void initListener(){
		VariableButtonUtil.listenerTextChange(etInputPhone,etInputPassword,btnLogin,btnLoginNormalD,btnLoginActiveD,btnLoginNormalC,
				btnLoginActiveC);
		btnLogin.setOnTouchListener(new LoginTouchListener());
		//注册
		tvRegister.setOnClickListener(this);
		//忘记密码
		tvForgetPassword.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.tv_register){
			mLoginPresenter.startRegisterActivity(this);
		}
		if(v.getId()==R.id.tv_forget_password){
			mLoginPresenter.startResetActivity(this);
		}
	}
	
	/**
	 * button抬起按下事件的监听处理
	 */
	private class LoginTouchListener implements View.OnTouchListener{
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_DOWN){
				btnLogin.setTextColor(btnLoginPressedC);
				mLoginPresenter.login();
			}
			if(event.getAction()==MotionEvent.ACTION_UP){
				btnLogin.setTextColor(btnLoginActiveC);
			}
			return true;
		}
	}
	
}
