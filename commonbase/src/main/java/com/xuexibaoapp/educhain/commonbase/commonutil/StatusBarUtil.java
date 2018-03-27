package com.xuexibaoapp.educhain.commonbase.commonutil;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;

/**
 * Created by zhaowenjie on 2018/3/21.
 *
 * @desc 状态栏工具类
 * 已有的选项：状态栏更改颜色..
 */

public class StatusBarUtil {
	/**
	 * 设置状态栏的颜色 默认状态栏图标为白色 ，可根据isBlack来设定状态栏颜色为白的特殊情况
	 * @param activity
	 * @param colorId 颜色ID: R.color.xx
	 * @param isBlack
	 */
	public static void setStatusBarColor(Activity activity,int colorId,boolean isBlack) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = activity.getWindow();
			if(isBlack){
				activity.getWindow().getDecorView().
						setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			}
			window.setStatusBarColor( activity.getResources().getColor(colorId));
		}
	}
}
