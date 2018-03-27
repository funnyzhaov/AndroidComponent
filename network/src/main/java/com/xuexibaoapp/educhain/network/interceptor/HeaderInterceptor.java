package com.xuexibaoapp.educhain.network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhaowenjie
 * @time 2018/3/17 18:41
 * @desc 配置请求头
 **/

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalrequest = chain.request();
        Request.Builder builder = originalrequest.newBuilder()
                .header("xx", "xx")
                .method(originalrequest.method(), originalrequest.body());
        Request request = builder.build();
        return chain.proceed(request);
    }
}
