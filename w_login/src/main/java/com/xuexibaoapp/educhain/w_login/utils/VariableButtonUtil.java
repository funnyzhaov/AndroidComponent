package com.xuexibaoapp.educhain.w_login.utils;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zhaowenjie on 2018/3/23.
 *
 * @desc
 */

public class VariableButtonUtil {
	/**
	 * 定制化的按钮变化规则  登录 注册 修改密码
	 * @param tPhone 电话 11位
	 * @param tCode  密码/验证码 6位以上
	 * @param button next
	 * @param normal 正常的背景资源
	 * @param active 可点击的北京资源
	 * @param resColorNormal
	 * @param resColorActicve
	 */
	public static void listenerTextChange(final EditText tPhone, final EditText tCode, final Button button,
	                                      final Drawable normal, final Drawable active, final int resColorNormal,
	                                      final int resColorActicve){
		
		button.setEnabled(false);
		tPhone.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int codeLen=tCode.getText().toString().length();
				int phoneLen=tPhone.getText().toString().length();
				if(phoneLen!=11){
					button.setBackground(normal);
					button.setTextColor(resColorNormal);
					button.setEnabled(false);
					return;
				}
				if(codeLen>5){
					button.setBackground(active);
					button.setTextColor(resColorActicve);
					button.setEnabled(true);
				}else {
					button.setBackground(normal);
					button.setTextColor(resColorNormal);
					button.setEnabled(false);
				}
				
			}
			@Override
			public void afterTextChanged(Editable s) {
			
			}
		});
		
		tCode.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int codeLen=tCode.getText().toString().length();
				int phoneLen=tPhone.getText().toString().length();
				if(phoneLen!=11){
					button.setBackground(normal);
					button.setTextColor(resColorNormal);
					button.setEnabled(false);
					return;
				}
				if(codeLen>5){
					button.setBackground(active);
					button.setTextColor(resColorActicve);
					button.setEnabled(true);
				}else {
					button.setBackground(normal);
					button.setTextColor(resColorNormal);
					button.setEnabled(false);
				}
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			
			}
		});
		
	}
	
	/**
	 * 验证码页Button点击效果
	 * @param submitBtn
	 * @param etCode
	 * @param normal
	 * @param active
	 * @param resColorNormal
	 * @param resColorActicve
	 */
	public static void listenCodeBtnChange(final Button submitBtn,final EditText etCode,final Drawable normal, final Drawable active, final int resColorNormal,
	                                       final int resColorActicve){
		submitBtn.setEnabled(false);
		etCode.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int codeLen=etCode.getText().toString().length();
				if(codeLen<6){
					submitBtn.setBackground(normal);
					submitBtn.setTextColor(resColorNormal);
					submitBtn.setEnabled(false);
					return;
				}else{
					submitBtn.setBackground(active);
					submitBtn.setTextColor(resColorActicve);
					submitBtn.setEnabled(true);
				}
				
			}
			@Override
			public void afterTextChanged(Editable s) {
			
			}
		});
	}
}
