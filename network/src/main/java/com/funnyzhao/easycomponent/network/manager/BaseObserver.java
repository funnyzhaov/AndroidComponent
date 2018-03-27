package com.funnyzhao.easycomponent.network.manager;

import android.content.Context;
import android.widget.Toast;

import com.funnyzhao.easycomponent.network.exception.ExceptionHandle;
import com.funnyzhao.easycomponent.network.utils.NetUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author zhaowenjie
 * @time 2018/3/17 20:01
 * @desc 观察者基类
 **/

public abstract class BaseObserver<T> implements Observer<T> {
    
    private Context mContext;
    
    public BaseObserver(Context context) {
        mContext = context;
    }
	
	
	@Override
    public void onError(Throwable e) {
        if(e instanceof ExceptionHandle.ResponseThrowable) {
            _onError((ExceptionHandle.ResponseThrowable) e);
        } else {
            _onError(new ExceptionHandle.ResponseThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }
    
    @Override
    public void onSubscribe(Disposable d) {
        if(!NetUtil.isNetworkAvailable(mContext)) {
            Toast.makeText(mContext, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
            onComplete();
            return;
        }
        //建立连接
        showDialog();
    }
    
    @Override
    public void onComplete() {
        //请求完毕
        hideDialog();
    }
    
    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    
    public abstract void _onNext(T t);
    
    public abstract void _onError(ExceptionHandle.ResponseThrowable throwable);
    
    /**
     * 进度条或对话框
     */
    protected abstract void showDialog();
    
    protected abstract void hideDialog();
}
