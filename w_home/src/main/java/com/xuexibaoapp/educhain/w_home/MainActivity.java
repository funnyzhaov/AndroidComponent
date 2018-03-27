package com.xuexibaoapp.educhain.w_home;



import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xuexibaoapp.educhain.commonbase.base.BaseActivity;

@Route(path = "/w_home/MainActivity")
public class MainActivity extends BaseActivity {
	private Button mButton;
	
	@Override
	protected void initEventBeforeView() {
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.w_home_activity_main;
	}
	
	@Override
	protected void initActivityEvent() {
		mButton=findViewById(R.id.btn_start);
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ARouter.getInstance().build("/w_login/LoginActivity").navigation();
			}
		});
	}
}
