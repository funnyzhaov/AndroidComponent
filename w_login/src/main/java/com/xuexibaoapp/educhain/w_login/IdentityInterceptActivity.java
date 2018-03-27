package com.xuexibaoapp.educhain.w_login;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xuexibaoapp.educhain.commonbase.base.BaseActivity;
import com.xuexibaoapp.educhain.w_login.utils.VariableButtonUtil;

/**
 * Created by zhaowenjie on 2018/3/27.
 *
 * @desc  This is a blocking page where the client is cooperating with the server to prevent the verification code from being brushed.
 *        you can get intent extra value if UserContrant.TOREGISTER. then go to RegisterActivity.another yet.
 *        get extra name is UserContrant.TOCODE
 */

public class IdentityInterceptActivity extends BaseActivity implements IdentityInterceptContract.View{
	
	private ImageView mIvClose;
	private ImageView mIVRefresh;
	private ImageView mIvCode;
	
	private EditText mEtCode;
	private Button mBtnSubmit;
	
	
	//Drawable资源
	Drawable btnNormalD;
	Drawable btnActiveD;
	
	//Color
	int btnNormalC; //正常颜色
	int btnActiveC;  //可点击颜色
	int btnPressedC; //点击后颜色
	
	@Override
	protected void initEventBeforeView() {
	
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.w_login_activity_iintercept;
	}
	
	@Override
	protected void initActivityEvent() {
		initListener();
		loadButtonAniRes();
		initButtonAni();
	}
	
	private void loadButtonAniRes() {
		btnNormalD=getDrawable(R.drawable.w_login_login_btn_normal);
		btnActiveD=getDrawable(R.drawable.w_login_login_btn_active);
		btnNormalC=getResources().getColor(R.color.w_login_btn_login_normal);
		btnActiveC=getResources().getColor(R.color.w_login_btn_login_active);
		btnPressedC=getResources().getColor(R.color.w_login_btn_login_pressed);
	}
	
	private void initListener() {
		mIvClose=findViewById(R.id.iv_close);
		mIVRefresh=findViewById(R.id.iv_refresh);
		mIvCode=findViewById(R.id.iv_code);
		
		mEtCode=findViewById(R.id.et_input_code);
		mBtnSubmit=findViewById(R.id.btn_submit);
		
	}
	
	private void initButtonAni() {
		//变化
		VariableButtonUtil.listenCodeBtnChange(mBtnSubmit,mEtCode,btnNormalD,btnActiveD,btnNormalC,
				btnActiveC);
		mBtnSubmit.setOnTouchListener(new IdentityInterceptActivity.CodeTouchListener());
	}
	
	@Override
	public String getInputCode() {
		return mEtCode.getText().toString().trim();
	}
	
	@Override
	public void showMsg(String msg) {
		Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * button抬起按下事件的监听处理
	 */
	private class CodeTouchListener implements View.OnTouchListener{
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_DOWN){
				mBtnSubmit.setTextColor(btnPressedC);
			}
			if(event.getAction()==MotionEvent.ACTION_UP){
				mBtnSubmit.setTextColor(btnActiveC);
			}
			return true;
		}
	}
}
