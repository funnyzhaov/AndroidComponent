package com.xuexibaoapp.educhain.commonbase.commonutil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by zhaowenjie on 2018/3/24.
 *
 * @desc 倒计时工具类
 */

public class CountDownUtil {
	private  CountDownCall mCall;
	/**
	 * 倒计时功能，在宿主生命周期的onDestroy()中执行disposable.dispose()操作
	 * @param disposable
	 * @param max
	 * @param call
	 */
	public  void countDown(Disposable disposable,int max,CountDownCall call){
		mCall=call;
		call.start();
		disposable= Flowable.intervalRange(0,max,0,1, TimeUnit.SECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext(new Consumer<Long>() {
					@Override
					public void accept(Long aLong) throws Exception {
						mCall.onNext(aLong);
					}
				})
				.doOnComplete(new Action() {
					@Override
					public void run() throws Exception {
						mCall.onComplete();
					}
				})
				.subscribe();
	}
	
	public interface CountDownCall{
		/**
		 * 开始时执行的事件
		 */
		void start();
		
		/**
		 * 倒计时间隔执行事件
		 */
		void onNext(Long aLong);
		
		/**
		 * 结束时执行的事件
		 */
		void onComplete();
	}
}
