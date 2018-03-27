package com.xuexibaoapp.educhain.network.manager;

import android.content.Context;
import com.xuexibaoapp.educhain.network.apiservice.BaseApiService;
import com.xuexibaoapp.educhain.network.exception.ExceptionHandle;
import com.xuexibaoapp.educhain.network.interceptor.CacheInterceptor;
import com.xuexibaoapp.educhain.network.response.BaseResponse;
import com.xuexibaoapp.educhain.network.response.BaseResult;
import com.xuexibaoapp.educhain.network.utils.log.NetLog;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhaowenjie
 * @time 2018/3/17 13:54
 * @desc Retrofit管理器, 统一网络管理
 **/
public class RetrofitManager {
    //临时变量
    private static boolean DEBUG = true;
    
    private BaseApiService mApiService;
    private static final int DEFAULT_TIME = 5;    //超时时间
    private static final int DEFAULT_READ_TIME_OUT = 10;  //读操作超时时间
    private static final int DEFAULT_WRITE_TIME_OUT = 10;  //写操作超时时间
    private Retrofit mRetrofit;
    private static Context mContext;
    
    private RetrofitManager() {
        NetLog.init();
        //添加OKHTTP拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        NetLog.d(message);
                    }
                });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        
        //创建OkhttpClientBuilder
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);//错误重连
        if(DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }
        
        //缓存机制,将数据存储在应用包名下的data缓存目录
        File cacheDirectory = new File(mContext.getExternalCacheDir(), "responses");
        Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);
        CacheInterceptor cacheInterceptor = new CacheInterceptor(mContext);
        builder.cache(cache).addInterceptor(cacheInterceptor);
        
        //请求头interceptor配置
        
        //OkhttpClient
        OkHttpClient okHttpClient = builder.build();
        
        //创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseApiService.DEBUG_BASE_URL)
                .build();
    }
    
    private static class SingletonHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }
    
    /**
     * 获取RetrofitManager
     **/
    public static RetrofitManager getInstance(Context context) {
        mContext = context;
        return SingletonHolder.INSTANCE;
    }
    
    /**
     * 创建 Base Service
     **/
    public RetrofitManager createBaseApi() {
        mApiService = create(BaseApiService.class);
        return this;
    }
    
    /**
     * 创建service
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(final Class<T> service) {
        if(service == null) {
            throw new RuntimeException("Api service is null");
        }
        return mRetrofit.create(service);
    }
    
    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        
        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            return Observable.error(ExceptionHandle.handleException(throwable));
        }
    }
    
    /**
     * post表单请求
     * @param url api
     * @param parameters 提交数据
     * @param observer 事件处理
     */
    public void filedPost(String url, Map<String, String> parameters, BaseObserver<BaseResponse<BaseResult>> observer) {
        //加上需要切换的线程即可完成线程调度
        mApiService.filedRequest(url, parameters)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new HttpResponseFunc<BaseResponse<BaseResult>>())
                .subscribe(observer);
    }

}
