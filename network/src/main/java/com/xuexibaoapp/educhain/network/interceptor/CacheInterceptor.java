package com.xuexibaoapp.educhain.network.interceptor;

import android.content.Context;

import com.xuexibaoapp.educhain.network.utils.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhaowenjie
 * @time 2018/3/17 18:03
 * @desc 缓存拦截设置
 **/

public class CacheInterceptor implements Interceptor {
    private Context mContext;
    
    public CacheInterceptor(Context context) {
        mContext = context;
    }
    
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!NetUtil.isNetworkAvailable(mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if(NetUtil.isNetworkAvailable(mContext)) {
            int maxAge = 0;
            //有网络时，设置缓存时间为0,拉取最新数据
            response.newBuilder()
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .build();
        } else {
            //无网络时
            int maxstate = 60 * 60 * 24 * 7;
            response.newBuilder()
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public,only-if-cached,max-state=" + maxstate)
                    .build();
        }
        
        return response;
    }
}
