package com.funnyzhao.easycomponent.xuelian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.funnyzhao.easycomponent.componentservice.w_login.LoginService;

@Route(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	
	private Button mButton1;
	@Autowired()
	LoginService mService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mButton1=findViewById(R.id.btn_1);
		mButton1.setOnClickListener(this);
		ARouter.getInstance().inject(this);
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_1){
//			mService=(LoginService) ARouter.getInstance().build("/w_login/token").navigation();
//			String name=mService.getUserTokenKey();
//			Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
			
			//拉取home
			ARouter.getInstance().build("/w_home/MainActivity").navigation();
		}
	}
}
