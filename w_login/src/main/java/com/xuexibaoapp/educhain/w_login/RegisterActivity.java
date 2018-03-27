package com.xuexibaoapp.educhain.w_login;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xuexibaoapp.educhain.commonbase.base.BaseActivity;
import com.xuexibaoapp.educhain.commonbase.commonutil.CountDownUtil;
import com.xuexibaoapp.educhain.commonbase.commonutil.StatusBarUtil;
import com.xuexibaoapp.educhain.w_login.utils.VariableButtonUtil;

import io.reactivex.disposables.Disposable;


/**
 * Created by zhaowenjie on 2018/3/22.
 *
 * @desc 注册页面
 */
@Route(path = "/w_login/RegisterActivity")
public class RegisterActivity extends BaseActivity implements RegisterContract.View{
	private TextView tvTips;
	private EditText etInputPhone;
	private EditText etInputCode;
	private TextView tvGetCode;
	private Button btnNext;
	
	
	//Drawable资源
	Drawable btnNormalD;
	Drawable btnActiveD;
	
	//Color
	int btnNormalC; //正常颜色
	int btnActiveC;  //可点击颜色
	int btnPressedC; //点击后颜色
	
	int getCodeNormalC;
	int getCodeCountDownC;
	
	private RegisterPresenterImpl mPresenter;
	
	private Disposable mdDisposable;
	private CountDownUtil mCountDownUtil;
	@Override
	protected void initEventBeforeView() {
		mCountDownUtil=new CountDownUtil();
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.w_login_view_validation_code;
	}
	
	@Override
	protected void initActivityEvent() {
		//控件
		tvTips=findViewById(R.id.tv_code_page_tips);
		btnNext=findViewById(R.id.btn_next);
		etInputPhone=findViewById(R.id.et_input_phone);
		etInputCode=findViewById(R.id.et_input_code);
		tvGetCode=findViewById(R.id.tv_get_code);
		//P
		initPresenter();
		//UI
		StatusBarUtil.setStatusBarColor(this,R.color.w_login_white,true);
		tvTips.setText("注册用户");
		//button
		loadRes();
		initListener();
	}
	
	private void initPresenter(){
		mPresenter=new RegisterPresenterImpl(this,this);
	}
	
	/**
	 * 加载资源
	 */
	private void loadRes(){
		btnNormalD=getDrawable(R.drawable.w_login_login_btn_normal);
		btnActiveD=getDrawable(R.drawable.w_login_login_btn_active);
		btnNormalC=getResources().getColor(R.color.w_login_btn_login_normal);
		btnActiveC=getResources().getColor(R.color.w_login_btn_login_active);
		btnPressedC=getResources().getColor(R.color.w_login_btn_login_pressed);
		
		getCodeNormalC=getResources().getColor(R.color.w_login_get_code_normal);
		getCodeCountDownC=getResources().getColor(R.color.w_login_get_code_countdown);
	}
	
	/**
	 * 初始化监听事件
	 */
	private void initListener(){
		//变化
		VariableButtonUtil.listenerTextChange(etInputPhone,etInputCode,btnNext,btnNormalD,btnActiveD,btnNormalC,
				btnActiveC);
		btnNext.setOnTouchListener(new RegisterTouchListener());
		tvGetCode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(getPhone().length()==11){
					getCodeCountDown();
				}else{
					showTips("请输入正确的手机号");
				}
			}
		});
	}
	
	/**
	 * button抬起按下事件的监听处理
	 */
	private class RegisterTouchListener implements View.OnTouchListener{
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_DOWN){
				btnNext.setTextColor(btnPressedC);
				//检查验证码
				mPresenter.checkPhoneCode();
			}
			if(event.getAction()==MotionEvent.ACTION_UP){
				btnNext.setTextColor(btnActiveC);
			}
			return true;
		}
	}
	
	/**
	 * 获取验证码UI操作
	 */
	private void getCodeCountDown(){
		mCountDownUtil.countDown(mdDisposable, 59, new CountDownUtil.CountDownCall() {
			@Override
			public void start() {
				tvGetCode.setEnabled(false);
			}
			
			@Override
			public void onNext(Long aLong) {
				tvGetCode.setText((59 - aLong) + " s");
				tvGetCode.setTextColor(getCodeCountDownC);
			}
			
			@Override
			public void onComplete() {
						//倒计时完毕置为可点击状态
						tvGetCode.setEnabled(true);
						tvGetCode.setText("获取验证码");
						tvGetCode.setTextColor(getCodeNormalC);
			}
		});
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
	public String getCode() {
		return etInputCode.getText().toString();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//解除订阅
		if (mdDisposable != null) {
			mdDisposable.dispose();
		}
	}
}
