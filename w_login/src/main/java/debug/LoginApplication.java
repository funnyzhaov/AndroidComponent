package debug;

import com.alibaba.android.arouter.launcher.ARouter;
import com.funnyzhao.easycomponent.commonbase.base.BaseApplication;
import com.funnyzhao.easycomponent.commonbase.commonutil.DebugManager;

/**
 * Created by zhaowenjie on 2018/3/23.
 *
 * @desc
 */

public class LoginApplication extends BaseApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		if(DebugManager.isDebug){
			ARouter.openLog();     // 打印日志
			ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
		}
		ARouter.init( this );
	}
}

